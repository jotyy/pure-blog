val valiktorVersion = "0.12.0"
val kotestVersion = "4.3.1"
val mockkVersion = "1.10.3-jdk8"

plugins {
    kotlin("jvm")
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(project(":core"))
    implementation(project(":data"))
    implementation("org.valiktor:valiktor-core:$valiktorVersion")

    testImplementation("io.mockk:mockk:$mockkVersion")
    testImplementation("io.kotest:kotest-runner-junit5:$kotestVersion")
    testImplementation("io.kotest:kotest-property:$kotestVersion")
}
