pluginManagement {
    enableFeaturePreview("VERSION_CATALOGS")
    dependencyResolutionManagement {
        versionCatalogs {
            val libs by creating {
                val signer = version("signer", "0.0.10")
                val later = "0.0.51"
                val expect = "0.0.21"

                alias("later-ktx").to("tz.co.asoft:later-ktx:$later")
                alias("expect-coroutines").to("tz.co.asoft:expect-coroutines:$expect")
            }
        }
    }
    repositories {
        google()
        jcenter()
        gradlePluginPortal()
        mavenCentral()
    }

    resolutionStrategy {
        eachPlugin {
            if (requested.id.namespace == "com.android") {
                useModule("com.android.tools.build:gradle:${requested.version}")
            }
        }
    }
}

rootProject.name = "signer"
include(":signer-core")