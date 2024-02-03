package amini.projects.project_renamer

class Logit {

    companion object {
        private fun log(text: String) {
            println(text)
        }

        fun logFileRename(indicator: String = "", oldName: String, newName: String, status: Boolean) = this.log(
            "$indicator FileRename: ${oldName.format("30s%")} to ${newName.format("30s%")} : (${if (status) "Success" else "Failed!"})"
        )

        fun logDirRename(indicator: String = "", oldName: String, newName: String, status: Boolean) = this.log(
            "$indicator DirRename: ${oldName.format("30s%")}  to ${newName.format("30s%")}: (${if (status) "Success" else "Failed!"})"
        )

        fun logReadingFile(parentPath: String) = "----- Reading files from: $parentPath"
        fun logShowResults(numberOfSuccessRename: Int, numberOfFailedRename: Int) =
            println("Success: $numberOfSuccessRename\nFail: $numberOfFailedRename")

        fun logInfo(s: String) = println(s)

    }


}