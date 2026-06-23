pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "RamprasadSuperApp"
include(":app")
include(":core:bluetooth")
include(":core:wifi")
include(":core:navigation")
include(":features:dashboard")
include(":features:home")
include(":features:devices")
include(":features:logs")
include(":features:profile")
include(":features:notifications")
include(":retailstore")
