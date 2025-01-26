plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.compose.compiler)
    publishing
}

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
