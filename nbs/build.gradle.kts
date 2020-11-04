plugins {
    id("org.jlleitschuh.gradle.ktlint") version "9.2.1"
}

version = "1.0.0"

dependencies {
    implementation("com.google.guava:guava:30.0-jre")
    compileOnly("org.projectlombok:lombok:1.18.16")
    annotationProcessor("org.projectlombok:lombok:1.18.16")
    annotationProcessor("org.jetbrains:annotations:16.0.2")
}

