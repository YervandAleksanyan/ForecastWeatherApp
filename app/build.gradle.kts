plugins {
    id(Dependencies.Plugins.applicationPlugin)
    id(Dependencies.Plugins.kotlinPlugin)
    id(Dependencies.Plugins.kotlinKapt)
}


android {
    compileSdk = Config.compileSdk

    defaultConfig {
        applicationId = Config.packageName
        minSdk = Config.minSdk
        targetSdk = Config.targetSdk
        versionCode = Config.versionCode
        versionName = Config.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            @Suppress("UnstableApiUsage")
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            @Suppress("UnstableApiUsage")
            isMinifyEnabled = false
            proguardFiles(
                @Suppress("UnstableApiUsage")
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        release {
            isMinifyEnabled = true
        }
        debug {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = Config.jvmTarget
    }
    sourceSets.all {
        java.srcDir("src/$name/kotlin")
    }
    @Suppress("UnstableApiUsage")
    buildFeatures {
        compose = true
    }
    @Suppress("UnstableApiUsage")
    composeOptions {
        kotlinCompilerExtensionVersion = Dependencies.composeVersion
    }
    @Suppress("UnstableApiUsage")
    packagingOptions {
        resources {
            excludes += Config.excludes
        }
    }
}

dependencies {
    implementation(project(":core"))
    implementation(project(":data"))
    implementation(project(":domain"))
    implementation(project(":splash_screen"))
    implementation(project(":home_screen"))



    implementation(Dependencies.Android.ktxCore)
    implementation(Dependencies.Android.runtimeLifecycle)
    testImplementation(Dependencies.Android.jUnit)
    androidTestImplementation(Dependencies.Android.jUnitTest)
    androidTestImplementation(Dependencies.Android.espresso)

    //Compose
    implementation(Dependencies.Compose.activityCompose)
    androidTestImplementation(Dependencies.Compose.composeJUnit)
    debugImplementation(Dependencies.Compose.composeUITooling)
    implementation(Dependencies.Compose.ui)
    implementation(Dependencies.Compose.material)
    implementation(Dependencies.Compose.preview)
    implementation(Dependencies.Compose.composeFoundation)
    implementation(Dependencies.Compose.composeMaterialIcons)
    implementation(Dependencies.Compose.composeMaterialIconsExtended)
    implementation(Dependencies.Compose.composeConstraintLayout)
    implementation(Dependencies.Compose.composeLiveData)
    implementation(Dependencies.Compose.composeViewModel)
    implementation(Dependencies.Compose.swipeRefresh)
    implementation(Dependencies.Compose.composeMaterial3)

    // Navigation Compose
    implementation(Dependencies.Compose.composeNavigation)
    implementation(Dependencies.Compose.accompanistPermissions)
    implementation(Dependencies.Compose.accompanist)
    implementation(Dependencies.Compose.accompanistAnimationNavigation)

    // Permissions
    implementation(Dependencies.Compose.permissions)

    // Koin main features for Android
    implementation(Dependencies.Koin.koinAndroid)
    implementation(Dependencies.Koin.koinCompose)
    implementation(Dependencies.Koin.koinCore)

    // Coroutines
    implementation(Dependencies.Coroutines.coroutinesAndroid)
    implementation(Dependencies.Coroutines.coroutinesCore)

    // Orbit
    implementation(Dependencies.Orbit.orbitCore)
    implementation(Dependencies.Orbit.orbitViewModel)
    implementation(Dependencies.Orbit.orbitCompose)
    testImplementation(Dependencies.Orbit.orbitTestImplementation)

}