rootProject.name = "ChatManager"

listOf(
    "version/5.0.0/paper" to "paper-5.0.0",
    "version/5.0.0/core" to "core-5.0.0",

    "publish" to "publish",

    "paper" to "paper",
).forEach(::includeProject)

fun includeProject(pair: Pair<String, String>): Unit = includeProject(pair.first, pair.second)

fun includeProject(name: String, block: ProjectDescriptor.() -> Unit) {
    include(name)
    project(":$name").apply(block)
}

fun includeProject(path: String, name: String) {
    includeProject(name) {
        this.name = "${rootProject.name.lowercase()}-$name"
        this.projectDir = File(path)
    }
}

fun includeProject(name: String) {
    includeProject(name) {
        this.name = "${rootProject.name.lowercase()}-$name"
    }
}