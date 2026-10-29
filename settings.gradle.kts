# SDK 37 (verified present locally: android-37.2)
# AGP 8.7.0 (latest stable reference)
# Build Tools 37.0.0 (verified present locally)

pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Veqq"
include(":app")
