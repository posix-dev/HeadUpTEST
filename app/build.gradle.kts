plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.compose)
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.example.headuptest"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.headuptest"
        minSdk = 30
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
    implementation(project(":core:ui"))
    implementation(project(":core:db"))
    implementation(project(":core:model"))
    implementation(project(":core:nav"))
    implementation(project(":feature:parameters"))
    implementation(project(":feature:summary"))
    implementation(project(":feature:diary"))
    implementation(project(":feature:new_entry"))
    implementation(project(":feature:parameters:shared"))
    //voyager
    implementation(libs.voyager.navigator.tab)

    implementation("tech.thdev:flow-call-adapter-factory:1.0.1")

    //hilt
    implementation(libs.hilt)
    kapt(libs.hilt.compiler)

    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.material3)

    // вынести потом
    implementation(libs.voyager.navigator.transitions)

    //hilt
    implementation(libs.hilt)
    kapt(libs.hilt.compiler)
    implementation(libs.hilt.nav)

    // gson
    implementation(libs.gson)

    //room
//    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    kapt(libs.room.compiler)
}