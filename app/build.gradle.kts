plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("com.google.devtools.ksp")
}

android {
    namespace = "fr.mekbal_dev.recipe_app"
    compileSdk = 35

    defaultConfig {
        applicationId = "fr.mekbal_dev.recipe_app"
        minSdk = 28
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures{
        viewBinding = true
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

    //j'ai ajouter ca
    //implementation ("androidx.core:core:1.2.0")

    //navigation componenet
    val nav_version = "2.8.4"
    //implementation("androidx.navigation:navigation-fragment:$nav_version")
    //implementation("androidx.navigation:navigation-ui:$nav_version")

    implementation ("androidx.navigation:navigation-fragment-ktx:$nav_version")
    implementation ("androidx.navigation:navigation-ui-ktx:$nav_version")

    //size screen
    implementation("com.intuit.sdp:sdp-android:1.0.6")
    implementation("com.intuit.ssp:ssp-android:1.0.6")

    implementation("network.chaintech:sdp-ssp-compose-multiplatform:1.0.4")

    //loaded animation
    implementation("pl.droidsonroids.gif:android-gif-drawable:1.2.17")

    //json to kotlin
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.3.0")

    //for images
    implementation("com.github.bumptech.glide:glide:4.12.0")

    //videoModel mvvm
    val lifecycle_version = "2.4.0-rc01"
    //val lifecycle_version = "2.8.7"
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version")
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.0")
    //implementation ("android.arch.lifecycle:extensions:1.1.0")
    //implementation ("android.lifecycle:extensions:1.1.0")

    val room_version = "2.6.1"
    implementation("androidx.room:room-runtime:$room_version")

    ksp("androidx.room:room-compiler:$room_version")
    // optional - Kotlin Extensions and Coroutines support for Room
    implementation("androidx.room:room-ktx:$room_version")



}