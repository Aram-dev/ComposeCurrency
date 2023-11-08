
import BuildAndroidConfig.TEST_INSTRUMENTATION_RUNNER
import dependencies.AnnotationProcessorsDependencies
import dependencies.Dependencies
import extensions.*
import org.gradle.kotlin.dsl.implementation

plugins {
    id(BuildPlugins.ANDROID_APPLICATION)
    id(BuildPlugins.KOTLIN_ANDROID)
    id(BuildPlugins.KOTLIN_PARCELIZE)
    id(BuildPlugins.KOTLIN_KAPT)
    id(BuildPlugins.NAVIGATION)
    id(BuildPlugins.HILT)
//    id("androidx.room")
//    id(BuildPlugins.KOTLIN_KSP)
//    id("com.google.devtools.ksp") version "1.9.20-1.0.14"

}

android {
    namespace = "com.example.composecurrency"
    compileSdk = BuildAndroidConfig.COMPILE_SDK_VERSION

    defaultConfig {
        applicationId = BuildAndroidConfig.APPLICATION_ID
        minSdk = BuildAndroidConfig.MIN_SDK_VERSION
        targetSdk = BuildAndroidConfig.TARGET_SDK_VERSION

        versionCode = BuildAndroidConfig.VERSION_CODE
        versionName = BuildAndroidConfig.VERSION_NAME

//        javaCompileOptions {
//            annotationProcessorOptions {
//                arguments += mapOf(
//                    "room.schemaLocation" to "$projectDir/schemas",
//                    "room.incremental" to "true"
//                )
//            }
//        }

        testInstrumentationRunner = TEST_INSTRUMENTATION_RUNNER
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    composeOptions {
        kotlinCompilerExtensionVersion = BuildDependenciesVersions.COMPOSE_COMPILER
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
    buildFeatures {
        compose = true
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    api(project(BuildModules.DATA))
    implementation(project(BuildModules.DOMAIN))
    implementation(project(BuildModules.FEATURES))
    implementation(Dependencies.NAVIGATION_UI)
    implementation(Dependencies.LOGGING)
    implementation(Dependencies.RETROFIT)
    implementation(Dependencies.RETROFIT_GSON)
    platformImplementation(Dependencies.COMPOSE_BOM)
    implementation(Dependencies.COMPOSE_UI)
    implementation(Dependencies.COMPOSE_UI_GRAPHICS)
    implementation(Dependencies.COMPOSE_PREVIEW)
    implementation(Dependencies.COMPOSE_MATERIAL_3)
    implementation(Dependencies.NAVIGATION_COMPOSE)

    implementation(Dependencies.HILT)
    kapt(AnnotationProcessorsDependencies.HILT)

    implementation(Dependencies.ROOM_KTX)
    implementation(Dependencies.ROOM_RUNTIME)
    kapt(AnnotationProcessorsDependencies.ROOM)

    addTestsDependencies()
}