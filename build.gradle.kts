import org.jetbrains.dokka.gradle.DokkaTask
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val ktorVersion = "1.4.3"
val kotlinVersion = "1.4.20"
val logbackVersion ="1.2.1"
val exposedVersion ="0.28.1"
val hikariVersion ="3.4.5"
val postgresqlVersion ="42.2.14"
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
        mavenCentral()
        jcenter()
    }

    kotlin.sourceSets["main"].kotlin.srcDirs("src")
    kotlin.sourceSets["test"].kotlin.srcDirs("test")

    sourceSets["main"].resources.srcDirs("resources")
    sourceSets["test"].resources.srcDirs("testresources")
}

kotlin.sourceSets["main"].kotlin.srcDirs("src")
kotlin.sourceSets["test"].kotlin.srcDirs("test")

sourceSets["main"].resources.srcDirs("resources")
sourceSets["test"].resources.srcDirs("testresources")

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion")
    implementation("io.ktor:ktor-server-netty:$ktorVersion")
    implementation("ch.qos.logback:logback-classic:$logbackVersion")
    implementation("io.ktor:ktor-server-core:$ktorVersion")
    implementation("io.ktor:ktor-auth:$ktorVersion")
    implementation("io.ktor:ktor-auth-jwt:$ktorVersion")
    implementation("io.ktor:ktor-jackson:$ktorVersion")
    implementation("org.jetbrains.exposed:exposed-core:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-dao:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-jodatime:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-jdbc:$exposedVersion")
    implementation("org.postgresql:postgresql:$postgresqlVersion")
    implementation("org.koin:koin-ktor:$koinVersion")
    implementation("com.zaxxer:HikariCP:$hikariVersion")
    implementation("com.apurebase:kgraphql:$kGraphQLVersion")
    implementation("com.apurebase:kgraphql-ktor:$kGraphQLVersion")
    implementation("org.valiktor:valiktor-core:$valiktorVersion")
    testImplementation("io.ktor:ktor-server-tests:$ktorVersion")
    testImplementation("org.junit.jupiter:junit-jupiter:$junitVersion")
    testImplementation("org.koin:koin-test:$koinVersion")
}

tasks.test {
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
