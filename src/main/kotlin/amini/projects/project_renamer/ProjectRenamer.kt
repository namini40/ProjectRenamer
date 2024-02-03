package amini.projects.project_renamer

import amini.projects.project_renamer.file_handler.FileHandler

class ProjectRenamer(projectRenamerConfig: ProjectRenamerConfig) {

    private val fileHandler = FileHandler(projectRenamerConfig)

    fun rename() {
        fileHandler.renameAllFileNames()
        fileHandler.replaceAllFileContent()
    }
}