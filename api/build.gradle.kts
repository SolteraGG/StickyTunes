plugins {
	kotlin("jvm")
}

version = "1.0.0"
group = "com.dumbdogdiner.tunes.api"

repositories {
	maven {
		url = uri("https://papermc.io/repo/repository/maven-public/")
	}
}

dependencies {
	compileOnly("com.destroystokyo.paper", "paper-api", "1.16.5-R0.1-SNAPSHOT")
}
