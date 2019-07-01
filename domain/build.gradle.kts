import org.jetbrains.kotlin.config.KotlinCompilerVersion

plugins {
    kotlin("jvm")
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    //Kotlin
    implementation(kotlin("stdlib-jdk7", KotlinCompilerVersion.VERSION))
    //kotlinx.coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.2.2")
    //Koin
    implementation("org.koin:koin-core:1.0.2")

    //JUnit
    testImplementation("junit:junit:4.12")
}

