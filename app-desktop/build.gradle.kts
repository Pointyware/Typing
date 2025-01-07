plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.compose.compiler)
    application
    publishing
}

dependencies {
    implementation(projects.appShared)

    implementation(compose.ui)
    implementation(compose.material3)
    implementation(compose.materialIconsExtended)
    implementation(compose.material3AdaptiveNavigationSuite)
    implementation(libs.compose.navigation)

    implementation(libs.kotlinx.coroutinesSwing)
    implementation(compose.desktop.currentOs)
    implementation(compose.components.uiToolingPreview)
}

application {
    mainClass = "org.pointyware.typing.desktop.ApplicationKt"
}
