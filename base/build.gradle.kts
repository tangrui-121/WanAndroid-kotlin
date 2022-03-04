

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-android")
    id("kotlin-kapt")
}

android {
    compileSdk = AppConfig.compileSdkVersion

    defaultConfig {
        minSdk = AppConfig.minSdkVersion
        targetSdk = AppConfig.targetSdkVersion

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    //https://stackoverflow.com/questions/63898030/kotlin-dsl-add-new-sourceset
    sourceSets {
        named("main") {
            res.srcDir("src/main/res-sw")
        }
    }
}

dependencies {
    api(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    api(WanAndroidExt.kotlin)
    api(WanAndroidExt.appcompat)
    api(WanAndroidExt.constraintlayout)
    api(WanAndroidExt.retrofit)
    api(WanAndroidExt.converterGson)
    api(WanAndroidExt.gson)
    api(WanAndroidExt.coroutines)
    api(WanAndroidExt.okhttp3Log)
    api(WanAndroidExt.material)
    api(WanAndroidExt.logger)
    api(WanAndroidExt.fragmentKtx)
    api(WanAndroidExt.activityKtx)
    api(WanAndroidExt.ImmersionBar)
    api(WanAndroidExt.BasequickAdapter)
    api(WanAndroidExt.cookie)
}