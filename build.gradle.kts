// Top-level build file where you can add configuration options common to all sub-projects/modules.

// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    dependencies {
        // Plugin Google Services cho Firebase
        classpath("com.google.gms:google-services:4.4.2")
    }
}

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
}


// (Tuỳ chọn) cấu hình chung nếu bạn cần thêm nhiều module sau này
allprojects {
    repositories {
        google()
        mavenCentral()
    }
}
