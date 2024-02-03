package amini.projects.project_renamer.file_handler

import amini.projects.project_renamer.*
import java.io.File
import java.io.IOException
import java.nio.charset.StandardCharsets
import java.nio.file.Files

class FileHandler(val config: ProjectRenamerConfig) {

    private val fileList = mutableListOf<File>()

    init {
        readFileList()
    }

    private fun readFileList() {
        Logit.logReadingFile(config.pathToProjectDirectory)
        File(config.pathToProjectDirectory).walkTopDown().forEach {
            fileList.add(it)
        }
    }

    fun renameAllFileNames() {
        readFileList()
        var numberOfSuccessRename = 0
        var numberOfFailedRename = 0
        fileList.forEach {
            if (renameFile(it, replaceContentTagged(it.name))) numberOfSuccessRename++ else numberOfFailedRename++
        }
        Logit.logShowResults(numberOfSuccessRename, numberOfFailedRename)
    }

    fun replaceAllFileContent() {
        readFileList()
        var numberOfSuccessRename = 0
        var numberOfFailedRename = 0
        fileList.forEach {
            if (replaceFileContent(it)) numberOfSuccessRename++ else numberOfFailedRename++
        }
    }

    private fun excludeNonReplaceFiles(fileExtension: String): Boolean {
        return Models.isFileExtensionExcluded(config.projectType, fileExtension)
    }

    private fun replaceContentTagged(inputFileName: String): String {
        var result = inputFileName
        when (config.projectType) {
            ProjectType.Default -> Models.DefaultRenamerMap
        }.forEach { (old, new) ->
            if (result.contains(old)) {
                result = result.replace(old, new)
            }
        }
        return result
    }


    private fun replaceFileContent(file: File): Boolean {
        if (excludeNonReplaceFiles(file.extension)) return false
        return try {
            val resultList = mutableListOf<String>()
            file.readLines().forEach {
                resultList.add(replaceContentTagged(it))
            }
            Files.write(file.toPath(), resultList, StandardCharsets.UTF_8)
            true
        } catch (e: IOException) {
            false
        }
    }

    private fun renameFile(file: File, newFileName: String): Boolean {
        val newFile = File(file.parentFile, newFileName)
        val oldName = file.name
        try {
            file.renameTo(newFile)
            Logit.logFileRename(oldName = oldName, newName = newFileName, status = true)
            return true
        } catch (e: IOException) {
            Logit.logFileRename(oldName = oldName, newName = newFileName, status = false)
            return false
        }
    }

    private fun renameDir(dir: File, newDirName: String): Boolean {
        val newDir = File(dir.parentFile, newDirName)
        val oldDir = dir.name
        try {
            Files.move(dir.toPath(), newDir.toPath())
            Logit.logDirRename(oldName = oldDir, newName = newDirName, status = true)
            return true
        } catch (e: IOException) {
            Logit.logDirRename(oldName = oldDir, newName = newDirName, status = true)
            return false
        }
    }

}