import org.jetbrains.dokka.gradle.DokkaTask
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val ktorVersion = "1.4.3"
val kotlinVersion = "1.4.20"
val logbackVersion = "1.2.1"
val exposedVersion = "0.28.1"
val hikariVersion = "3.4.5"
val postgresqlVersion = "42.2.14"
val koinVersion = "2.1.6"
val kGraphQLVersion = "0.16.0"
val valiktorVersion = "0.12.0"
val junitVersion = "5.6.2"


plugins {
    base
    kotlin("jvm") version "1.4.20"
    id("org.jetbrains.dokka") version "0.10.1"
    id("com.diffplug.gradle.spotless") version "4.5.1"
}

group = "top.jotyy"
version = "0.0.1"

repositories {
    mavenLocal()
    jcenter()
    maven("https://maven.aliyun.com/repository/public")
    maven("https://maven.aliyun.com/repository/central")
    maven("https://maven.aliyun.com/repository/jcenter")
    maven("https://kotlin.bintray.com/ktor")
}

subprojects {
    group = "top.jotyy"
    version = "0.0.1"

    apply(plugin = "kotlin")

    repositories {
        mavenLocal()
        jcenter()
        maven("https://maven.aliyun.com/repository/public")
        maven("https://maven.aliyun.com/repository/central")
        maven("https://maven.aliyun.com/repository/jcenter")
        maven("https://kotlin.bintray.com/ktor")
    }

    tasks.withType<KotlinCompile>().all {
        version = JavaVersion.VERSION_1_8
    }

    tasks.withType<Test> {
        useJUnitPlatform()
        testLogging {
            events("passed", "skipped", "failed")
        }
    }
}

dependencies {
    implementation(kotlin("stdlib"))
}

tasks.withType<Test> {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}

spotless {
    kotlin {
        target("**/*.kt")
        ktlint().userData(mapOf("disabled_rules" to "no-wildcard-imports"))
        trimTrailingWhitespace()
        endWithNewline()
    }
}

tasks {
    withType<DokkaTask> {
        outputFormat = "html"
        outputDirectory = "$buildDir/dokka"
    }

    withType<KotlinCompile>().configureEach {
        kotlinOptions.jvmTarget = "1.8"
    }
}
