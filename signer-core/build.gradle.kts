plugins {
    kotlin("multiplatform")
    id("tz.co.asoft.library")
    id("io.codearte.nexus-staging")
    signing
}

kotlin {
    jvm { library() }
    js(IR) { library() }

//    val darwinTargets = listOf(
//        macosX64(),
//        ios(),
//        watchos(),
//        tvos()
//    )
//
//    val linuxTargets = listOf(
//        linuxX64(),
//        linuxArm64(),
//        linuxArm32Hfp()
//    )
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(asoft("later-ktx", vers.asoft.later))
            }
        }
        val commonTest by getting {
            dependencies {
                api(asoft("expect-coroutines", vers.asoft.expect))
            }
        }
    }
}

aSoftOSSLibrary(
    version = vers.asoft.signer,
    description = "A platform agnostic color library using css"
)
