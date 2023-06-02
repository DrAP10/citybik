plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.base"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.base"
        minSdk = 23
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(project(Dependencies.datasourceModule))
    implementation(project(Dependencies.presentationModule))

    implementation(Dependencies.koinAndroid)
    implementation(Dependencies.coreKtx)
    implementation(Dependencies.lifecycleRuntime)
    implementation(Dependencies.activityCompose)
    testImplementation(Dependencies.junit)
    androidTestImplementation(Dependencies.androidTestJunit)
    androidTestImplementation(Dependencies.androidTestEspresso)

}