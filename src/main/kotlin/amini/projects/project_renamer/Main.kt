package amini.projects.project_renamer

fun main() {
    val projectPath = "/some/where/on/disk"
    // before anything consider adding your mapping as a project type in config and provide mapping key,values
    val config = ProjectRenamerConfig(projectPath)
    val renamer = ProjectRenamer(config)
    renamer.rename()
}