plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "org.pointyware.typing.android"
    compileSdk = 34
    defaultConfig {
        applicationId = "org.pointyware.typing.android"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        compose = true
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(projects.appShared)
    implementation(libs.androidx.composeUi)
    implementation(libs.androidx.composePreview)
    implementation(libs.androidx.composeMaterial3)
    implementation(libs.androidx.activityCompose)
    debugImplementation(libs.androidx.composeTooling)
}
