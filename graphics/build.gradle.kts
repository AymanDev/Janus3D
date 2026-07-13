plugins {
    id("java-library")
}

group = "com.janus3d"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    api(project(":core"))
}
