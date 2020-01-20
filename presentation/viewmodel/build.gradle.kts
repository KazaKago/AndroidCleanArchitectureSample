import org.jetbrains.kotlin.config.KotlinCompilerVersion

plugins {
    id("com.android.library")
    kotlin("android")
}

android {
    compileSdkVersion(29)
    defaultConfig {
        minSdkVersion(21)
        targetSdkVersion(29)
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    flavorDimensions("app")
    productFlavors {
        create("production") {
            dimension = "app"
        }
        create("staging") {
            dimension = "app"
        }
        create("develop") {
            dimension = "app"
        }
    }
    compileOptions {
        coreLibraryDesugaringEnabled = true
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    lintOptions {
        xmlReport = true
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    //Module
    implementation(project(":domain:usecase"))
    implementation(project(":domain:model"))
    //Kotlin
    implementation(kotlin("stdlib-jdk8", KotlinCompilerVersion.VERSION))
    //kotlinx.coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.3")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.3")
    //Koin
    implementation("org.koin:koin-androidx-viewmodel:2.0.1")
    //AndroidX KTX
    implementation("androidx.core:core-ktx:1.1.0")
    //AndroidX Lifecycle
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.1.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.1.0")
    implementation("androidx.lifecycle:lifecycle-common-java8:2.1.0")

    //desugar_jdk_libs
    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:1.0.4")

    //JUnit
    testImplementation("junit:junit:4.13")

    //Espresso
    androidTestImplementation("androidx.test.ext:junit:1.1.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.2.0")
}