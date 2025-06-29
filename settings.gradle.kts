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
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "HeadUpTEST"
include(":app")
include(":feature")
include(":feature:parameters")
include(":core")
include(":core:ui")
include(":feature:diary")
include(":core:db")
include(":core:model")
include(":core:nav")
include(":feature:new_entry")
include(":feature:parameters:shared")
include(":core:common")
include(":feature:summary")
include(":feature:diary:shared")
