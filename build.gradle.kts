import org.jetbrains.kotlin.gradle.dsl.JvmTarget

group = "com.github.b1kku"

version = "0.0.1"

description = "Even more extra WorldGuard extra flags"

val projectName = "NotEnoughFlags"
val authors = listOf("B1kku")

// Any extra variables accessible to resources
val extras =
    mapOf(
        "projectName" to projectName,
        "main" to "${project.group}.${projectName.toLowerCase()}.${projectName}",
        "authors" to authors.joinToString(", ")
    )

extra.apply { extras.forEach { (key, value) -> set(key, value) } }

plugins { kotlin("jvm") version "2.0.0" }

repositories {
  mavenCentral()
  maven("https://repo.papermc.io/repository/maven-public/")
  maven("https://maven.enginehub.org/repo/")
}

dependencies {
  implementation(kotlin("stdlib"))
  implementation(kotlin("reflect"))
  compileOnly("io.papermc.paper:paper-api:1.20.1-R0.1-SNAPSHOT")
  compileOnly("com.sk89q.worldguard:worldguard-bukkit:7.0.0")
}

tasks {
  processResources { filesMatching("**/plugin.yml") { expand(project.properties) } }
  compileKotlin { compilerOptions { jvmTarget.set(JvmTarget.JVM_17) } }
}
