package amini.projects.project_renamer

/**
 * holds mapping one by one values in FILES and FOLDERS to replaced in a renamer map
 */
class Models {
    companion object {
        val DefaultRenamerMap = mapOf(
            "firstOldValue" to "firstNewValue",
            "secondOldValue" to "secondNewValue"
        )

        private val DefaultExcludedExtensions = listOf<String>()
        fun isFileExtensionExcluded(projectType: ProjectType, fileExtension: String): Boolean {
            when (projectType) {
                ProjectType.Default -> DefaultExcludedExtensions
            }.forEach {
                if (it == fileExtension) return true
            }
            return false

        }
    }
}