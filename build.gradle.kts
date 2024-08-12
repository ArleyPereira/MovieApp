plugins {
    id("com.android.application") version Versions.agpVersion apply false
    id("org.jetbrains.kotlin.android") version Versions.kotlinVersion apply false
    id("com.google.dagger.hilt.android") version Versions.hiltVersion apply false
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin") version Versions.secretsGradleVersion apply false
    id("com.google.gms.google-services") version Versions.googleServicesVersion apply false
    id("androidx.navigation.safeargs") version Versions.navigationVersion apply false
    id("com.google.devtools.ksp") version "1.9.10-1.0.13" apply false
}