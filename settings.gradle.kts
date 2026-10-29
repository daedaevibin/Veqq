// SDK 37 (verified present locally: android-37.2 / android-37.0)
// AGP 9.5.0 (updated via merged PR), build tools 37.0.0

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
