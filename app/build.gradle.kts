plugins {
    alias(libs.plugins.androidApplication)
    id("org.jetbrains.kotlin.android")
    // Otros plugins
    id ("kotlin-android")
    id ("kotlin-kapt") // Habilita kapt para procesar anotaciones de Kotlin
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
    viewBinding{
        enable=true
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }



    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.legacy.support.v4)
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.7")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.7")
    implementation("androidx.core:core-ktx:+")
    implementation(libs.room.ktx)
    // Otras dependencias
    implementation ("androidx.room:room-runtime:2.4.0") // Dependencia para Room en tiempo de ejecución
    kapt ("androidx.room:room-compiler:2.4.0") // Dependencia para el compilador de anotaciones de Room
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

}