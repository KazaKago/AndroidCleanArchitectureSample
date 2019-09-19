import org.jetbrains.kotlin.config.KotlinCompilerVersion

plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
}

android {
    compileSdkVersion(29)
    defaultConfig {
        minSdkVersion(21)
        targetSdkVersion(29)
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("proguard-rules.pro")
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }
    viewBinding {
        isEnabled = true
    }
    lintOptions {
        xmlReport = true
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    //Module
    implementation(project(":domain"))
    //Kotlin
    implementation(kotlin("stdlib-jdk7", KotlinCompilerVersion.VERSION))
    //kotlinx.coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.1")
    //Koin for AndroidX
    implementation("org.koin:koin-androidx-viewmodel:2.0.1")
    //AndroidX AppCompat
    api("androidx.appcompat:appcompat:1.1.0")
    //AndroidX ConstraintLayout
    api("androidx.constraintlayout:constraintlayout:1.1.3")
    //AndroidX Material
    api("com.google.android.material:material:1.0.0")
    //AndroidX Lifecycle
    api("androidx.lifecycle:lifecycle-viewmodel-ktx:2.1.0")
    api("androidx.lifecycle:lifecycle-livedata-ktx:2.1.0")
    kapt("androidx.lifecycle:lifecycle-compiler:2.1.0")
    //AndroidX KTX
    implementation("androidx.core:core-ktx:1.1.0")
    implementation("androidx.fragment:fragment-ktx:1.1.0")
    //PlayServices OSS Licenses
    implementation("com.google.android.gms:play-services-oss-licenses:17.0.0")
    //Groupie
    implementation("com.xwray:groupie:2.5.1")
    //Picasso
    implementation("com.squareup.picasso:picasso:2.71828")

    //JUnit
    testImplementation("junit:junit:4.12")

    //Espresso
    androidTestImplementation("androidx.test:runner:1.2.0")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.2.0")
}
