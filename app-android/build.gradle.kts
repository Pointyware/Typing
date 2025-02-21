import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.compose.compiler)
}

kotlin {
    jvmToolchain(11)

    androidTarget {
        compilations.all {
            compileTaskProvider.configure {
                compilerOptions {
                    jvmTarget.set(JvmTarget.JVM_1_8)
                }
            }
        }
    }

    sourceSets {
        val androidMain by getting {
            dependencies {
                implementation(projects.appShared)
                implementation(libs.androidx.composePreview)
                implementation(libs.androidx.activityCompose)

                implementation(compose.ui)
                implementation(compose.material3)
                implementation(compose.material3AdaptiveNavigationSuite)
                implementation(compose.components.resources)

                implementation(libs.koin.android)
            }
        }

        val androidUnitTest by getting {
            dependencies {
                implementation(libs.koin.test)
            }
        }
    }
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    debugImplementation(libs.androidx.composeTooling)
}

compose.resources {
    generateResClass = always
    publicResClass = true
    packageOfResClass = "org.pointyware.typing.android"
}
