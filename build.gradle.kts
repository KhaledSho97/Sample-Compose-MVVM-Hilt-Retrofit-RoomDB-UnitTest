buildscript {

    dependencies {
        classpath("com.android.tools.build:gradle:${libs.versions.android.gradle}")
        //classpath ("com.google.devtools.ksp:com.google.devtools.ksp.gradle.plugin:1.7.0-1.0.6")

    }
}

// Top-level build file where you can add configuration options common to all sub-projects/modules.
@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.com.android.application) apply false
    alias(libs.plugins.org.jetbrains.kotlin.android) apply false
    alias(libs.plugins.com.google.dagger.hilt.android) apply false
    alias(libs.plugins.androidx.navigation.safeargs) apply false
    alias(libs.plugins.kotlin.kapt) apply false
    alias(libs.plugins.kotlin.parcelize) apply false
}
true // Needed to make the Suppress annotation work for the plugins block