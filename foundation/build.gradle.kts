import com.vanniktech.maven.publish.AndroidSingleVariantLibrary
import com.vanniktech.maven.publish.SonatypeHost
import org.jetbrains.kotlin.konan.properties.Properties
import java.io.FileInputStream

plugins {
    id("com.android.library")
    id("kotlin-android")
    id("com.google.devtools.ksp")
    id("org.jetbrains.kotlin.plugin.serialization")
    id("com.vanniktech.maven.publish")
    id("io.nativeblocks.nativeblocks-gradle-plugin").version("1.1.2")
}

val nativeblocksProps = Properties().apply {
    load(FileInputStream(File(rootProject.rootDir, "sample.nativeblocks.properties")))
}

android {
    namespace = "io.nativeblocks.foundation"
    compileSdk = 34

    defaultConfig {
        minSdk = 26
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        getByName("release") {
            buildConfigField("String", "VERSION", "\"${ModuleInfo.VERSION}\"")
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
        getByName("debug") {
            buildConfigField("String", "VERSION", "\"${ModuleInfo.VERSION}\"")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.14"
    }
}

mavenPublishing {
    publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL)
    coordinates(ModuleInfo.GROUP_ID, ModuleInfo.ARTIFACT_ID, ModuleInfo.VERSION)
    signAllPublications()
    configure(
        AndroidSingleVariantLibrary(
            variant = "release",
            sourcesJar = true,
            publishJavadocJar = true,
        )
    )

    pom {
        name.set(ModuleInfo.ARTIFACT_ID)
        description.set(ModuleInfo.DESCRIPTION)
        url.set(ModuleInfo.URL)
        licenses {
            license {
                name = "NATIVEBLOCKS TERMS OF SERVICE"
                url = "https://nativeblocks.io/terms-of-service"
            }
        }
        developers {
            developer {
                name = "Nativeblocks"
                email = "dev@nativeblocks.io"
            }
        }
        scm {
            connection = "scm:git:github.com/nativeblocks/nativeblocks-foundation-android.git"
            developerConnection =
                "scm:git:ssh://github.com/nativeblocks/nativeblocks-foundation-android.git"
            url = "https://github.com/nativeblocks/nativeblocks-foundation-android"
        }
    }
}

nativeblocks {
    endpoint = nativeblocksProps["endpoint"] as String
    authToken = nativeblocksProps["authToken"] as String
    organizationId = nativeblocksProps["organizationId"] as String
    basePackageName = "io.nativeblocks.foundation"
    moduleName = "Foundation"
}

ksp {
    arg("basePackageName", "io.nativeblocks.foundation")
    arg("moduleName", "Foundation")
}

dependencies {
    //==========================kotlin===========================
    implementation("androidx.core:core-ktx:1.13.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.9.0")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3")
    //==========================compose==========================
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.6")
    implementation("androidx.activity:activity-compose:1.9.3")
    implementation("androidx.compose.material:material:1.7.4")
    implementation("androidx.compose.animation:animation:1.7.4")
    implementation("androidx.compose.ui:ui-tooling-preview:1.7.4")
    debugImplementation("androidx.compose.ui:ui-tooling:1.7.4")
    implementation("io.coil-kt:coil-compose:2.6.0")
    //==========================nativeblocks=======================
    implementation("io.nativeblocks:nativeblocks-android:1.5.0")
    ksp("io.nativeblocks:nativeblocks-compiler-android:1.3.0")
    implementation("io.nativeblocks:nativeblocks-compiler-android:1.3.0")
}

object ModuleInfo {
    const val GROUP_ID = "io.nativeblocks"
    const val ARTIFACT_ID = "nativeblocks-foundation-android"
    const val VERSION = "1.2.1"
    const val DESCRIPTION = "Nativeblocks foundation SDK for Android"
    const val URL = "https://nativeblocks.io"
}