plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "org.pointyware.typing.android"
    compileSdk = 35
    defaultConfig {
        applicationId = "org.pointyware.typing.android"
        minSdk = 24
        targetSdk = 35
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
    signingConfigs {
        create("release") {
            storeFile = file("keystore.jks")
            storePassword = providers.gradleProperty("keystore.password").getOrElse("no-pass")
            keyAlias = providers.gradleProperty("keystore.alias").getOrElse("no-alias")
            keyPassword = providers.gradleProperty("keystore.alias_password").getOrElse("no-alias-password")
        }
    }
    buildTypes {
        getByName("debug") {
            isMinifyEnabled = false
        }
        getByName("release") {
            isMinifyEnabled = true
            signingConfig = signingConfigs.getByName("release")
            proguardFiles.add(
                file("proguard-rules.pro")
            )
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
