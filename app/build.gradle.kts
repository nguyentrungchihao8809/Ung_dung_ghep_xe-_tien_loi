// app/build.gradle.kts (Đã tinh gọn)

plugins {
    // Sử dụng alias (từ libs.versions.toml) khi có thể để quản lý plugin nhất quán
    // alias(libs.plugins.android.application) // Giữ lại nếu đang dùng Alias
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose) // Plugin Compose (tương đương org.jetbrains.kotlin.plugin.compose)
    id("com.android.application")
    id("com.google.gms.google-services") // Bắt buộc cho Firebase
    id("org.jetbrains.kotlin.plugin.serialization") // Giữ lại cho Ktor/Kotlinx Serialization
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
        buildConfig = true
    }

    // ✅ Cấu hình Compose Compiler Extension (Rất quan trọng)
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.10" // Ví dụ: Cập nhật để tương thích với Compose BOM mới nhất
    }
}

dependencies {

    // --- 1. COMPOSE & ANDROIDX CƠ BẢN ---
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom)) // BOM quản lý các phiên bản Compose
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)

    // Các thư viện Compose/Lifecycle khác
    implementation("androidx.navigation:navigation-compose:2.8.0-beta05") // Cập nhật phiên bản
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.0-beta01") // Cập nhật phiên bản
    implementation("com.google.accompanist:accompanist-systemuicontroller:0.34.0")
    implementation("androidx.compose.runtime:runtime-livedata:1.6.8") // Cập nhật phiên bản

    // --- 2. GOOGLE MAPS & LOCATION SERVICES (Sử dụng cho Google Maps) ---
    // Google Maps Core SDK
    implementation("com.google.android.gms:play-services-maps:18.2.0")

    // Maps Compose (Thư viện để dùng Maps trong Jetpack Compose)
    implementation("com.google.maps.android:maps-compose:4.4.1")

    // Thư viện tiện ích cho Maps (PolyUtil, Heatmaps, v.v.)
    implementation("com.google.maps.android:android-maps-utils:3.4.0")

    // Google Location Services (FusedLocationProviderClient)
    implementation("com.google.android.gms:play-services-location:21.0.1")

    // --- 3. FIREBASE (Cho OTP/Authentication) ---
    implementation(platform("com.google.firebase:firebase-bom:33.1.0")) // Cập nhật BOM
    implementation("com.google.firebase:firebase-analytics-ktx")
    implementation("com.google.firebase:firebase-auth-ktx") // Dùng cho OTP/Đăng nhập

    // --- 4. NETWORKING & SERIALIZATION (Cho Geocoding/APIs) ---
    // Retrofit (Nếu bạn muốn dùng)
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    // Kotlinx Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.1") // Cập nhật phiên bản

    // Ktor (Nếu bạn muốn dùng)
    implementation("io.ktor:ktor-client-core:2.3.11")
    implementation("io.ktor:ktor-client-android:2.3.11")
    implementation("io.ktor:ktor-client-content-negotiation:2.3.11")
    implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.11")

    implementation("androidx.compose.foundation:foundation")

    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")
    implementation("com.google.android.gms:play-services-location:21.0.1")

    // --- 5. TESTING ---
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)


    implementation("androidx.preference:preference-ktx:1.2.1")


    // 1. Places SDK for Android - Để tìm kiếm và lấy chi tiết địa điểm
    implementation("com.google.android.libraries.places:places:3.4.0")

    // 2. Retrofit - Để tạo request gửi dữ liệu lên server riêng
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    // 3. Coroutines - Để xử lý bất đồng bộ an toàn (trong ViewModel)
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")
}

// ✅ Áp dụng Plugin Google Services (Bắt buộc cho Firebase)
apply(plugin = "com.google.gms.google-services")