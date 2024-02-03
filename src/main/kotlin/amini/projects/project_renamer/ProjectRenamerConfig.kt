package amini.projects.project_renamer

data class ProjectRenamerConfig(
    val pathToProjectDirectory:String,
    val projectType: ProjectType=ProjectType.Default,
)
