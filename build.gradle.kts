plugins {
    kotlin("jvm") version "1.3.72"
    java
}

repositories {
    mavenCentral()
}

subprojects {
    group = "com.dumbdogdiner.tunes"

    apply(plugin = "java")

    repositories {
        jcenter()
        mavenCentral()
    }
}
