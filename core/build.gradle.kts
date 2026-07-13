plugins {
    id("java-library")
}

group = "com.janus3d"
version = "unspecified"

repositories {
    mavenCentral()
}

val lwjglVersion = "3.4.1"
val jomlVersion = "1.10.9"
val `joml-primitivesVersion` = "1.10.0"
val lwjglNatives = "natives-windows"

dependencies {
    api(platform("org.lwjgl:lwjgl-bom:$lwjglVersion"))

    api("org.lwjgl:lwjgl")
    api("org.lwjgl:lwjgl::$lwjglNatives")

    api("org.lwjgl:lwjgl-openal")
    api("org.lwjgl:lwjgl-opengl")

    api("org.lwjgl:lwjgl-assimp")
    api("org.lwjgl:lwjgl-assimp::$lwjglNatives")

    api("org.lwjgl:lwjgl-jemalloc")
    api("org.lwjgl:lwjgl-jemalloc::$lwjglNatives")

    api("org.lwjgl:lwjgl-nanovg")
    api("org.lwjgl:lwjgl-nanovg::$lwjglNatives")

    api("org.lwjgl:lwjgl-renderdoc")

    api("org.joml:joml:$jomlVersion")
    api("org.joml:joml-primitives:${`joml-primitivesVersion`}")
}
