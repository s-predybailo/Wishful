import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("jvm")
    id("org.jetbrains.compose")
}

group = "ru.sp"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    google()
}

dependencies {
    // Note, if you develop a library, you should use compose.desktop.common.
    // compose.desktop.currentOs should be used in launcher-sourceSet
    // (in a separate module for demo project and in testMain).
    // With compose.desktop.common you will also lose @Preview functionality
    implementation(compose.desktop.currentOs)
    implementation("org.jetbrains.compose.material3:material3-desktop:1.6.0")
    implementation("cafe.adriel.voyager:voyager-core:1.1.0-alpha03")
    implementation("cafe.adriel.voyager:voyager-navigator-desktop:1.1.0-alpha03")
    implementation("cafe.adriel.voyager:voyager-transitions:1.1.0-alpha03")
    // Koin для Kotlin
    implementation("io.insert-koin:koin-core:3.5.3")
}

compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "Wishful"
            packageVersion = "1.0.0"
        }
    }
}
