plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("org.jetbrains.kotlin.kapt")
    id ("dagger.hilt.android.plugin")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.merajhossen20001.foodrecipe"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.merajhossen20001.foodrecipe"
        minSdk = 27
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
    }
}

dependencies {
    implementation (libs.androidx.core.splashscreen)
    implementation(libs.androidx.datastore.preferences)
    implementation("io.coil-kt:coil-compose:2.6.0")

    // Hilt
    implementation ("com.google.dagger:hilt-android:2.50")
    kapt ("com.google.dagger:hilt-compiler:2.50")

// Hilt + ViewModel
    implementation (libs.androidx.hilt.navigation.compose)
    kapt (libs.androidx.hilt.compiler)
    // Retrofit core
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    // Gson converter (for parsing JSON into data classes)
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")

    // OkHttp core
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    // OkHttp logging interceptor (logs request/response for debugging)
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")



    val room_version = "2.6.1" // latest stable

    // Room runtime
    implementation("androidx.room:room-runtime:$room_version")

    // Kotlin Extensions and Coroutines support for Room
    implementation("androidx.room:room-ktx:$room_version")

    // Room Compiler (use KSP instead of kapt)
    ksp("androidx.room:room-compiler:$room_version")

    // (Optional) Paging support for Room
    implementation("androidx.room:room-paging:$room_version")



    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.ui.text.google.fonts)
    implementation(libs.androidx.datastore.core.android)
    implementation(libs.androidx.datastore.preferences.core.android)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}