plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.dagger.hilt.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.saika.chatleaf"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.saika.chatleaf"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        debug {
            isDebuggable = true
            buildConfigField("String", "BASE_URL", "\"https://generativelanguage.googleapis.com/\"")
            buildConfigField("String", "OPENAI_API_KEY", "\"*********\"") // Generate Api key and add here
        }

        release {
            buildConfigField("String", "BASE_URL", "\"https://generativelanguage.googleapis.com/\"")
            buildConfigField("String", "OPENAI_API_KEY", "\"*********\"") // Generate Api key and add here
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
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
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
    implementation(libs.androidx.benchmark.common)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation(libs.dagger.hilt.android)
    ksp(libs.dagger.compiler)
    ksp(libs.dagger.hilt.android.compiler)

    //Retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.logging.interceptor)
    implementation(libs.okhttp)
    implementation(libs.converter.scalars)
    implementation(libs.encrypted.preferences)

    //Glide
    implementation(libs.landscapist.glide)
    implementation(libs.androidx.navigation.compose)

    //room
    implementation(libs.androidx.room.runtime)
    annotationProcessor(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)
    testImplementation(libs.androidx.room.testing)
    ksp(libs.androidx.room.compiler)
    implementation(kotlin("test"))

    //Coil
    implementation (libs.kotlinx.coroutines.android)
    implementation(libs.coil.compose)

    implementation (libs.hilt.navigation.compose)

    implementation(libs.accompanist.navigation.animation)

    // JUnit for unit testing
    testImplementation(libs.junit)

    // Coroutines test support
    testImplementation(libs.kotlinx.coroutines.test)

    // MockK for mocking API calls
    testImplementation(libs.mockk)

    // Retrofit MockWebServer (optional if using real API calls)
    testImplementation(libs.mockwebserver)

    // Lifecycle & ViewModel test support
    testImplementation(libs.androidx.core.testing)

    // Generative AI
    implementation(libs.generativeai)
}