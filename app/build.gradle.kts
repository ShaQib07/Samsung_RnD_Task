plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.parcelize)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
}

android {
    namespace = "com.shakib.samsungrndtask"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.shakib.samsungrndtask"
        minSdk = 24
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
    // Specifies one flavor dimension.
    flavorDimensions += "version"
    productFlavors {
        create("dev") {
            manifestPlaceholders += mapOf(
                "APP_NAME" to "SamsungRnDTaskDev",
                "env" to "dev"
            )
            dimension = "version"
            applicationIdSuffix = ".dev"
            buildConfigField("String", "BASE_URL", "\"https://jsonplaceholder.typicode.com/\"")
            buildConfigField("Boolean", "USE_WORK_MANAGER", "true")
        }
        create("staging") {
            manifestPlaceholders += mapOf(
                "APP_NAME" to "SamsungRnDTaskStaging",
                "env" to "staging"
            )
            dimension = "version"
            applicationIdSuffix = ".staging"
            buildConfigField("String", "BASE_URL", "\"https://jsonplaceholder.typicode.com/\"")
            buildConfigField("Boolean", "USE_WORK_MANAGER", "false")
        }
        create("production") {
            manifestPlaceholders += mapOf(
                "APP_NAME" to "SamsungRnDTask",
                "env" to "production"
            )
            dimension = "version"
            applicationIdSuffix = ".production"
            //signingConfig signingConfigs.production
            buildConfigField("String", "BASE_URL", "\"https://jsonplaceholder.typicode.com/\"")
            buildConfigField("Boolean", "USE_WORK_MANAGER", "false")
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
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation(platform(libs.okhttp.bom))
    implementation(libs.okhttp)
    implementation(libs.okhttp.logging.interceptor)
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.gson)

    implementation(libs.hilt.android)
    implementation(libs.hilt.navigation.compose)
    ksp(libs.hilt.android.compiler)

    implementation(libs.work.manager)
    implementation(libs.hilt.work.manager)
    ksp(libs.hilt.work.manager.compiler)

    implementation(libs.coil)

    testImplementation(libs.coroutine.test)

    testImplementation(libs.mockk)
    androidTestImplementation(libs.mockk.android)

    // Kotlin Coroutines test dependency (optional, for coroutine testing)
    // testImplementation "org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3"

    // JUnit for unit testing
    // testImplementation 'junit:junit:4.13.2'

    // AndroidX Test dependencies (optional)
    // androidTestImplementation "androidx.test.ext:junit:1.1.5"
    // androidTestImplementation "androidx.test.espresso:espresso-core:3.5.1"
}