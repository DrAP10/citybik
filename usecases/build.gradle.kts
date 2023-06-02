plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    implementation(project(Dependencies.domainModule))
    implementation(project(Dependencies.dataModule))

    implementation(Dependencies.koinCore)
    implementation(Dependencies.coroutinesAndroid)
}