plugins {
    kotlin("jvm")
}

group = "top.forestfairy.cga"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:2.0.20")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}