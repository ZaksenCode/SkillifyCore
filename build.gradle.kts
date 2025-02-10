plugins {
    kotlin("jvm") version "2.1.10"
    kotlin("plugin.serialization") version "2.1.0"

    id("com.github.johnrengelman.shadow") version "8.1.1"
    id("maven-publish")
}

group = "me.zaksen"
version = "1.0.15"

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/") {
        name = "papermc-repo"
    }
    maven("https://oss.sonatype.org/content/groups/public/") {
        name = "sonatype"
    }
    maven("https://repo.xenondevs.xyz/releases") {
        name = "INV-UI"
    }
    maven("https://repo.dmulloy2.net/repository/public/") {
        name = "ProtocolLib"
    }
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.20.1-R0.1-SNAPSHOT")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("com.charleskorn.kaml:kaml:0.66.0")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.8.0")
    compileOnly("com.comphenix.protocol:ProtocolLib:4.8.0")
}

val targetJavaVersion = 17
kotlin {
    jvmToolchain(targetJavaVersion)
}

tasks.build {
    dependsOn("shadowJar")
}

tasks.processResources {
    val props = mapOf("version" to version)
    inputs.properties(props)
    filteringCharset = "UTF-8"
    filesMatching("plugin.yml") {
        expand(props)
    }
}

publishing {
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/ZaksenCode/SkillifyCore")
            credentials {
                username = (project.findProperty("gpr.user") ?: System.getenv("GITHUB_USERNAME")) as String
                password = (project.findProperty("gpr.key") ?: System.getenv("GITHUB_TOKEN")) as String
            }
        }
    }
    publications {
        register<MavenPublication>("gpr") {
            artifactId = "skillify-core"
            version = project.version as String
            from(components["java"])
        }
    }
}