import dependencies.AnnotationProcessorsDependencies
import dependencies.Dependencies
import extensions.addComposeMaterialDependencies
import extensions.kapt

plugins {
    id("commons.android-library")
    id(BuildPlugins.KOTLIN_KAPT)
}

android {
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = BuildDependenciesVersions.COMPOSE_COMPILER
    }
    namespace = "com.example.common"
}

dependencies {
    api(Dependencies.APPCOMPAT)
    api(Dependencies.CORE_KTX)
    api(Dependencies.TIMBER)
    implementation(Dependencies.COROUTINES)
    implementation(Dependencies.HILT)
    kapt(AnnotationProcessorsDependencies.HILT)
    addComposeMaterialDependencies()
}