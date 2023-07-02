plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    namespace = "co.devine_solutions.runtimer.android"
    compileSdk = 33
    defaultConfig {
        applicationId = "co.devine_solutions.runtimer.android"
        minSdk = 29
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.7"
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

    // project modules
    implementation(project(":shared"))

    // libraries
    implementation("androidx.compose.ui:ui:1.4.3")
    implementation("androidx.compose.ui:ui-tooling:1.4.3")
    implementation("androidx.compose.ui:ui-tooling-preview:1.4.3")
    implementation("androidx.compose.foundation:foundation:1.4.3")
    implementation("androidx.compose.material:material:1.4.3")
    implementation("androidx.activity:activity-compose:1.7.2")

    // Koin
    implementation("io.insert-koin:koin-androidx-compose:3.4.5")
    implementation("io.insert-koin:koin-androidx-compose-navigation:3.4.5")
    implementation("io.insert-koin:koin-core:3.4.0")

    // Compose navigation
    implementation("androidx.navigation:navigation-compose:2.6.0")

}