buildscript {
    repositories {
        google()
        mavenCentral()
    }
}


plugins {
    id(Dependencies.Plugins.applicationPlugin) version Dependencies.Plugins.version apply false
    id(Dependencies.Plugins.libraryPlugin) version Dependencies.Plugins.version apply false
    id(Dependencies.Plugins.kotlinPlugin) version Dependencies.Plugins.versionKotlinPlugin apply false
 }


tasks {
    val clean by registering(Delete::class) {
        delete(buildDir)
    }
}
