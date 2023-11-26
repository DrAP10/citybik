plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.base.presentation"
    compileSdk = 33

    defaultConfig {
        minSdk = 23
        targetSdk = 33

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildFeatures {
        compose = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
}

dependencies {
    implementation(project(Dependencies.usecasesModule))
    implementation(project(Dependencies.domainModule))

    implementation(Dependencies.koinAndroid)
    implementation(Dependencies.koinCompose)
    implementation(Dependencies.activityCompose)
    implementation(Dependencies.accompanist)
    implementation(Dependencies.composeUi)
    implementation(Dependencies.composeUiToolingPreview)
    implementation(Dependencies.composeRuntime)
    implementation(Dependencies.composeLiveData)
    implementation(Dependencies.materialCompose)
    implementation(Dependencies.coilCompose)
    implementation(Dependencies.coreKtx)
    implementation(Dependencies.appCompat)
    implementation(Dependencies.material)
    implementation(Dependencies.legacySupport)
    implementation(Dependencies.liveData)
    implementation(Dependencies.viewModel)
    testImplementation(Dependencies.junit)
    androidTestImplementation(Dependencies.androidTestJunit)
    androidTestImplementation(Dependencies.androidTestEspresso)
    androidTestImplementation(Dependencies.composeJunit)
    debugImplementation(Dependencies.composeUiTooling)
    debugImplementation(Dependencies.composeUiTestManifest)
}