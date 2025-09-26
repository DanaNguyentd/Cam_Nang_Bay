plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)

    id("com.google.protobuf") version "0.9.4"
}

android {
    namespace = "com.example.hotrobay"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.hotrobay"
        minSdk = 29
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

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:3.18.0"
    }
    generateProtoTasks {
        all().forEach { task ->
            task.builtins {
                create("java") {
                    option("lite") // Use lite for Android
                }
            }
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    // DataStore Preferences
    implementation(libs.androidx.datastore.preferences)

    // Proto DataStore
    implementation("androidx.datastore:datastore:1.0.0")

    // Proto serialization
    implementation("com.google.protobuf:protobuf-javalite:3.18.0")

    // Navigation
    implementation("androidx.navigation:navigation-compose:2.9.4") // ✅ Correct navigation lib

    // ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.9.3")

    //Icons extra
    implementation ("androidx.compose.material:material-icons-extended:1.5.4")
    implementation(libs.androidx.foundation.android)

    //For Offline Google Translate
    implementation ("com.google.mlkit:translate:17.0.1")

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}