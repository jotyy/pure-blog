plugins {
    application
    kotlin("jvm")
}

application {
    mainClassName = "io.ktor.server.netty.EngineMain"
}

val ktorVersion = "1.4.3"
val logbackVersion = "1.2.1"
val koinVersion = "2.1.6"

dependencies {
    implementation(kotlin("stdlib"))
    implementation(project(":domain"))
    implementation("io.ktor:ktor-server-netty:$ktorVersion")
    implementation("ch.qos.logback:logback-classic:$logbackVersion")
    implementation("io.ktor:ktor-server-core:$ktorVersion")
    implementation("io.ktor:ktor-auth:$ktorVersion")
    implementation("io.ktor:ktor-auth-jwt:$ktorVersion")
    implementation("io.ktor:ktor-jackson:$ktorVersion")
    implementation("org.koin:koin-ktor:$koinVersion")
    testImplementation("junit", "junit", "4.12")
}
