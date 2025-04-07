plugins {
    alias(libs.plugins.kotlinMultiplatform)
}

kotlin {
    jvm("desktop")

    sourceSets {
        val desktopMain by getting
        desktopMain.dependencies {
            implementation(libs.kotlinx.coroutines.swing)
        }
        commonMain.dependencies {
            implementation(libs.kotlin.stdlib)
            implementation(libs.koin.compose)
            implementation(libs.koin.compose.viewModel)
            implementation(libs.koin.core)
            implementation("com.google.firebase:firebase-admin:9.2.0")
            implementation("com.google.guava:guava:31.1-jre")
            implementation("org.slf4j:slf4j-simple:2.0.7")
        }

        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}
