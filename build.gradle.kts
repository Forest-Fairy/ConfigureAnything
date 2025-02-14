plugins {
    kotlin("jvm") version "2.0.20"
}
rootProject.version = "0.2.14"
// 声明变量
group = "top.spray.cga"
version = rootProject.version

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}