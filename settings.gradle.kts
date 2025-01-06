enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Typing_Test"
include(
    ":app-android",
    ":app-desktop",
//    ":app-ios",
    ":app-web",
)
include(":app-shared")

include(
    ":feature-typing",
)

include(
    ":core-data",
    ":core-ui"
)
