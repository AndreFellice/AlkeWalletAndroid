plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)

    id("kotlin-android")
    id("kotlin-kapt")
   
}

android {
    namespace = "com.example.alkewalletandroid"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.alkewalletandroid"
        minSdk = 29
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        viewBinding = true
    }

    buildFeatures {
        dataBinding = true
    }
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.legacy.support.v4)
    implementation(libs.core.ktx)
    implementation(libs.navigation.fragment.ktx)
    implementation(libs.navigation.ui.ktx)

    // MVVM Lifecycle y LiveData
    implementation(libs.lifecycle.viewmodel.ktx.v251)
    implementation(libs.lifecycle.livedata.ktx.v251)
    implementation(libs.androidx.databinding.runtime)

    // Testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    // Fragment KTX
    implementation(libs.fragment.ktx.v154)

    // Room
    val room_version = "2.4.0"
    implementation(libs.room.runtime)
    annotationProcessor(libs.room.compiler)

    // Kapt - Soporte para Kotlin Extensions y Coroutines en Room
    implementation(libs.room.ktx.v240)
    kapt(libs.androidx.room.compiler.v261)

    // Opcional - Helpers de Test
    testImplementation(libs.androidx.room.testing)

    // Lifecycle
    implementation(libs.androidx.lifecycle.extensions)

    // corrutines
    implementation(libs.kotlinx.coroutines.android)

    //Retrofit
    implementation (libs.retrofit)
    implementation(libs.converter.gson)

    // Retrofit with Scalar Converter
    implementation (libs.converter.scalars)

    // Glide
    implementation (libs.glide)
    annotationProcessor (libs.compiler)
}