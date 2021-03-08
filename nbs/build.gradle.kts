import org.gradle.api.tasks.testing.logging.TestLogEvent

version = "1.0.0"

dependencies {
    implementation("com.google.guava:guava:30.0-jre")
    implementation(project(":StickyTunesAPI"))

    testImplementation(platform("org.junit:junit-bom:5.7.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

sourceSets {
    test {
        resources {
            include("**/*.nbs")
        }
    }
}

tasks {
    test {
        useJUnitPlatform()
        testLogging {
            events = mutableSetOf(TestLogEvent.FAILED, TestLogEvent.SKIPPED, TestLogEvent.SKIPPED)
        }
    }

    named("replaceText") {
        val destFile = File(rootDir.absolutePath + "/nbs/src/main/kotlin/${targetPackage}/Version.kt")
    }
}
