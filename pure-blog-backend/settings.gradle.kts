pluginManagement {
    repositories {
        gradlePluginPortal()
        jcenter()
    }
}

rootProject.name = "pure-blog"

include("application")
include("core")
include("domain")
include("data")
