import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
    kotlin("kapt")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

tasks.withType(KotlinCompile::class).all {
    kotlinOptions.jvmTarget = "1.8"
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    // Module
    implementation(project(":domain:model"))
    implementation(project(":domain:repository"))
    // Kotlin
    implementation(kotlin("stdlib-jdk8"))
    // kotlinx.coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.3")
    // Dagger Hilt
    implementation("com.google.dagger:hilt-core:2.35")
    kapt("com.google.dagger:hilt-compiler:2.35")
    // StoreFlowable.kt
    implementation("com.github.kazakago.storeflowable:storeflowable-core:3713375")

    // JUnit
    testImplementation("junit:junit:4.13.2")
}

