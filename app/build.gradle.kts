plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("dagger.hilt.android.plugin")
    id("kotlin-parcelize")
    id("kotlin-kapt")
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.sparshchadha.stocktracker"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.sparshchadha.stocktracker"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.2"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // region GSON
    implementation (libs.gson)
    // endregion

    // region Retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.logging.interceptor)
    implementation(libs.adapter.rxjava3)
    // endregion

    // region Chucker
    debugImplementation(libs.chucker.library)
    releaseImplementation(libs.chucker.library.no.op)
    // endregion

    // region fragment navigation
    implementation(libs.fragment.navigation)
    // endregion

    // region Room
    implementation(libs.androidx.room.runtime)
    kapt(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)
    // endregion

    // region Compose
    implementation(libs.androidx.compose.material3.android)
    implementation(libs.androidx.lifecycle.runtime.compose)
    // endregion

    // region Lottie
    implementation(libs.lottie.compose)
    // endregion

    // region fragment navigation
    implementation(libs.fragment.navigation)
    // endregion

    // region Preference Datastore
    implementation(libs.androidx.datastore.preferences)
    // endregion

    // region sdp and ssp
    implementation(libs.sdp.android)
    implementation(libs.ssp.android)
    // endregion

    // region Dagger - Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)
    kapt(libs.androidx.hilt.compiler)
    // endregion
}