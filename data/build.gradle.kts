import dependencies.AnnotationProcessorsDependencies
import dependencies.Dependencies
import dependencies.TestDependencies
import extensions.kapt
import extensions.ksp
import extensions.testImplementation

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id(BuildPlugins.KOTLIN_KAPT)
}

android {
    namespace = "com.example.data"

    defaultConfig {
        compileSdk = BuildAndroidConfig.COMPILE_SDK_VERSION
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

dependencies {
    api(project(BuildModules.DOMAIN))
    implementation(Dependencies.RETROFIT)
    implementation(Dependencies.RETROFIT_GSON)

    implementation(Dependencies.HILT)
    kapt(AnnotationProcessorsDependencies.HILT)

    implementation(Dependencies.ROOM_KTX)
    implementation(Dependencies.ROOM_RUNTIME)
    kapt(AnnotationProcessorsDependencies.ROOM)

    testImplementation(TestDependencies.EXT)
    testImplementation(TestDependencies.JUNIT)


}