import org.jetbrains.kotlin.ir.backend.js.compile

plugins {
    kotlin("jvm")
}

group = "top.forestfairy"
version = "1.0-SNAPSHOT"
description = "ConfigureAnything Support Package For Nginx"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
//    implementation("org.projectlombok:lombok:1.18.24")
    api(project(":api"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}