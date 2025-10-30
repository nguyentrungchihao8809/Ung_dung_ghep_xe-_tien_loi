plugins {
//    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("com.android.application")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.myhatd"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.myhatd"
        minSdk = 24
        targetSdk = 36
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

//    implementation(libs.androidx.core.ktx)
//    implementation(libs.androidx.lifecycle.runtime.ktx)
//    implementation(libs.androidx.activity.compose)
//    implementation(platform(libs.androidx.compose.bom))
//    implementation(libs.androidx.compose.ui)
//    implementation(libs.androidx.compose.ui.graphics)
//    implementation(libs.androidx.compose.ui.tooling.preview)
//    implementation(libs.androidx.compose.material3)
//    testImplementation(libs.junit)
//    androidTestImplementation(libs.androidx.junit)
//    androidTestImplementation(libs.androidx.espresso.core)
//    androidTestImplementation(platform(libs.androidx.compose.bom))
//    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
//    debugImplementation(libs.androidx.compose.ui.tooling)
//    debugImplementation(libs.androidx.compose.ui.test.manifest)
//    implementation(platform("com.google.firebase:firebase-bom:34.4.0"))
//    implementation("com.google.firebase:firebase-analytics")
//    implementation("com.google.firebase:firebase-auth-ktx:22.3.0")
//    implementation("androidx.compose.ui:ui")
//    implementation("androidx.compose.material3:material3")
//    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0")
//    implementation(platform("com.google.firebase:firebase-bom:32.7.0"))
    dependencies {
        implementation(libs.androidx.core.ktx)
        implementation(libs.androidx.lifecycle.runtime.ktx)
        implementation(libs.androidx.activity.compose)
        implementation(platform(libs.androidx.compose.bom))
        implementation(libs.androidx.compose.ui)
        implementation(libs.androidx.compose.ui.graphics)
        implementation(libs.androidx.compose.ui.tooling.preview)
        implementation(libs.androidx.compose.material3)
        // Thêm foundation (sử dụng alias nếu đã định nghĩa, hoặc chuỗi đầy đủ)
        // Hoặc implementation("androidx.compose.foundation:foundation")
        testImplementation(libs.junit)
        androidTestImplementation(libs.androidx.junit)
        androidTestImplementation(libs.androidx.espresso.core)
        androidTestImplementation(platform(libs.androidx.compose.bom))
        androidTestImplementation(libs.androidx.compose.ui.test.junit4)
        debugImplementation(libs.androidx.compose.ui.tooling)
        debugImplementation(libs.androidx.compose.ui.test.manifest)
        implementation("androidx.navigation:navigation-compose:2.8.4")

        // Chỉ sử dụng một BOM Firebase
        implementation(platform("com.google.firebase:firebase-bom:34.4.0"))
        implementation("com.google.firebase:firebase-analytics")
        implementation("com.google.firebase:firebase-auth-ktx:22.3.0")
        implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0")
        implementation("com.google.maps.android:maps-compose:3.1.0")
        implementation("com.google.android.gms:play-services-maps:18.2.0")
        implementation("com.google.android.gms:play-services-location:21.0.1")
        implementation("com.google.accompanist:accompanist-systemuicontroller:0.34.0")
    }
}