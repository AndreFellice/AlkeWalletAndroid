plugins {
    alias(libs.plugins.androidApplication) // Mantén esta línea
    alias(libs.plugins.jetbrainsKotlinAndroid)

    kotlin("kapt") // Mantén este plugin para Kapt
   
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
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.1")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.5.1")
    implementation("androidx.databinding:databinding-runtime:7.3.1")

    // Testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    // Fragment KTX
    implementation("androidx.fragment:fragment-ktx:1.5.4")

    // Room
    val room_version = "2.4.0"
    implementation("androidx.room:room-runtime:$room_version")
    annotationProcessor("androidx.room:room-compiler:$room_version")

    // Kapt - Soporte para Kotlin Extensions y Coroutines en Room
    implementation("androidx.room:room-ktx:$room_version")
    kapt("androidx.room:room-compiler:2.6.1")

    // Opcional - Helpers de Test
    testImplementation("androidx.room:room-testing:$room_version")

    // Lifecycle
    implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")
}