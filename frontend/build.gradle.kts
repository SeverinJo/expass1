import com.github.gradle.node.npm.task.NpmTask

plugins {
    id("com.github.node-gradle.node") version "7.0.2"
}

node {
    version = "24.7.0"
    npmVersion = "11.6.0"
    download = true
}

tasks.register<NpmTask>("runBuild") {
    args = listOf("run", "build")
    workingDir = file(".")
}

tasks.register<Copy>("copyWebApp") {
    from("dist")
    into("../backend/src/main/resources/static")
    dependsOn("runBuild")
}
