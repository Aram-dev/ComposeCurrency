import dependencies.AnnotationProcessorsDependencies
import dependencies.Dependencies
import extensions.*
import org.gradle.kotlin.dsl.implementation

plugins {
    id("commons.android-library")
    id(BuildPlugins.NAVIGATION)
    id(BuildPlugins.KOTLIN_KAPT)
}

android {
    namespace = "com.example.features"
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = BuildDependenciesVersions.COMPOSE_COMPILER
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

    platformImplementation(Dependencies.COMPOSE_BOM)

    implementation(Dependencies.APPCOMPAT)
    implementation(Dependencies.LIFECYCLE_EXTENSIONS)
    implementation(Dependencies.NAVIGATION_COMPOSE)
    implementation(Dependencies.NAVIGATION_FRAGMENT)
    implementation(Dependencies.NAVIGATION_UI)
    implementation(Dependencies.MATERIAL)
    implementation(Dependencies.COIL)
    implementation(Dependencies.ACTIVITY_COMPOSE)
    implementation(Dependencies.HILT_NAVIGATION)
    implementation(Dependencies.RETROFIT)

    implementation(Dependencies.COMPOSE_PREVIEW)
    implementation(Dependencies.LIFECYCLE_RUNTIME_COMPOSE)
    debugImplementation(Dependencies.COMPOSE_UI_TOOLING)

    implementation(Dependencies.PAGING)
    kapt(AnnotationProcessorsDependencies.HILT)

    addComposeAllDependencies()
    addTestsDependencies()
}