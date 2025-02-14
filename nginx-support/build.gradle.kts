import org.jetbrains.kotlin.ir.backend.js.compile

plugins {
    kotlin("jvm")
}

group = "top.spray.cga"
version = rootProject.version
description = "ConfigureAnything Support Package For Nginx"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    api(project(":api"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}