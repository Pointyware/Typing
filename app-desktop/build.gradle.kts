plugins {
//    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.compose.compiler)
    publishing
}

kotlin {
    jvm()

    sourceSets {
        val jvmMain by getting {
            dependencies {
                implementation(projects.appShared)
                implementation(projects.featureTyping)

                implementation(libs.compose.navigation)
                implementation(libs.kotlinx.coroutinesSwing)

                implementation(compose.ui)
                implementation(compose.material3)
                implementation(compose.materialIconsExtended)
                implementation(compose.material3AdaptiveNavigationSuite)
                implementation(compose.components.resources)

                implementation(compose.desktop.currentOs)
                implementation(compose.components.uiToolingPreview)

                implementation(libs.koin.core)
            }
        }
        val jvmTest by getting {
            dependencies {
                implementation(libs.koin.test)
            }
        }
    }
}

compose {
    desktop {
        application {
            mainClass = "org.pointyware.typing.desktop.ApplicationKt"
        }
    }
    resources {
        generateResClass = always
        publicResClass = true
        packageOfResClass = "org.pointyware.typing.desktop"
    }
}
