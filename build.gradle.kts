plugins {
    kotlin("jvm") version "1.4.31"
    id("com.diffplug.spotless") version "5.8.2"
}

repositories {
    mavenCentral()
}

subprojects {
    repositories {
        jcenter()
        mavenCentral()
    }

    // apply plugins
    apply(plugin = "kotlin")
    apply(plugin =  "com.diffplug.spotless")

    spotless {
        kotlin {
            ktlint()
        }
    }
}
