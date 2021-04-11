plugins {
    kotlin("multiplatform")
    id("tz.co.asoft.library")
    id("io.codearte.nexus-staging")
    signing
}

kotlin {
    jvm { library() }
    js(IR) { library() }
    val darwinTargets = listOf(
        macosX64(),
        iosArm32(),
        iosX64(),
        iosArm64(),
        watchosArm64(),
        watchosArm32(),
        watchosX86(),
        tvosArm64(),
        tvosX64()
    )

    val linuxTargets = listOf(
        linuxX64()
    )
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(libs.later.ktx)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(libs.expect.coroutines)
            }
        }
    }
}

aSoftOSSLibrary(
    version = vers.signer,
    description = "A platform agnostic color library using css"
)
