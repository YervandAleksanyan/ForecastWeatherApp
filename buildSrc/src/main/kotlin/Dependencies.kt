object Dependencies {

    const val composeVersion = "1.2.0"

    object Plugins {
        const val version = "7.3.0"
        const val versionKotlinPlugin = "1.7.0"

        const val applicationPlugin = "com.android.application"
        const val libraryPlugin = "com.android.library"
        const val kotlinPlugin = "org.jetbrains.kotlin.android"
        const val kotlinKapt = "kotlin-kapt"
        const val kotlinParcelize = "kotlin-parcelize"
    }

    object Android {
        const val ktxCore = "androidx.core:core-ktx:1.7.0"
        const val runtimeLifecycle = "androidx.lifecycle:lifecycle-runtime-ktx:2.4.1"
        const val jUnit = "junit:junit:4.13.2"
        const val jUnitTest = "androidx.test.ext:junit:1.1.3"
        const val espresso = "androidx.test.espresso:espresso-core:3.4.0"
    }


    object Compose {

        const val activityCompose = "androidx.activity:activity-compose:1.4.0"
        const val ui = "androidx.compose.ui:ui:$composeVersion"
        const val material = "androidx.compose.material:material:$composeVersion"
        const val preview = "androidx.compose.ui:ui-tooling-preview:$composeVersion"
        const val accompanist = "com.google.accompanist:accompanist-systemuicontroller:0.18.0"
        const val composeUITooling = "androidx.compose.ui:ui-tooling:$composeVersion"
        const val composeJUnit = "androidx.compose.ui:ui-test-junit4:$composeVersion"
        const val composeFoundation = "androidx.compose.foundation:foundation:$composeVersion"
        const val composeMaterialIcons =
            "androidx.compose.material:material-icons-core:$composeVersion"
        const val composeMaterialIconsExtended =
            "androidx.compose.material:material-icons-extended:$composeVersion"
        const val composeLiveData = "androidx.compose.runtime:runtime-livedata:$composeVersion"
        const val composeConstraintLayout =
            "androidx.constraintlayout:constraintlayout-compose:1.0.0"
        const val composeViewModel = "androidx.lifecycle:lifecycle-viewmodel-compose:2.4.1"

        const val composeNavigation = "androidx.navigation:navigation-compose:2.4.2"

        const val accompanistPermissions = "com.google.accompanist:accompanist-permissions:0.0.2"
        const val permissions = "com.google.accompanist:accompanist-permissions:0.24.6-alpha"
        const val accompanistAnimationNavigation =
            "com.google.accompanist:accompanist-navigation-animation:0.25.0"
        const val composeMaterial3 =
            "androidx.compose.material3:material3:1.0.0-alpha14"

        const val swipeRefresh = "com.google.accompanist:accompanist-swiperefresh:0.26.0-alpha"

    }

    object Koin {
        private const val koinVersion = "3.1.5"

        const val koinAndroid = "io.insert-koin:koin-android:$koinVersion"
        const val koinCore = "io.insert-koin:koin-core:$koinVersion"
        const val koinCompose = "io.insert-koin:koin-androidx-compose:$koinVersion"
    }

    object Coroutines {
        private const val coroutineVersion = "1.6.4"

        const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutineVersion"
        const val coroutinesAndroid =
            "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutineVersion"
        const val TEST = "org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.1"
    }

    object GoogleMaps {
//        const val googleMaps = "com.google.android.gms:play-services-maps:18.0.2"
//        const val googleMapsKtx = "com.google.maps.android:maps-ktx:3.3.0"
        const val googleLocation = "com.google.android.gms:play-services-location:19.0.1"
//        const val googleMapsUtils = "com.google.maps.android:android-maps-utils:1.2.1"
//        const val composeMaps = "com.google.maps.android:maps-compose:2.1.1"


    }

    object Orbit {
        private const val orbitVersion = "4.3.2"
        const val orbitCore = "org.orbit-mvi:orbit-core:$orbitVersion"
        const val orbitViewModel = "org.orbit-mvi:orbit-viewmodel:$orbitVersion"
        const val orbitTestImplementation = "org.orbit-mvi:orbit-test:$orbitVersion"
        const val orbitCompose = "org.orbit-mvi:orbit-compose:$orbitVersion"
    }

    object Retrofit {
        private const val retrofitVersion = "2.9.0"

        const val retrofit = "com.squareup.retrofit2:retrofit:$retrofitVersion"
        const val gsonConverter = "com.squareup.retrofit2:converter-gson:$retrofitVersion"
        const val adapterRxjava3 = "com.squareup.retrofit2:adapter-rxjava3:$retrofitVersion"

    }

    object OkhttpClient {
        private const val okhttpVersion = "5.0.0-alpha.2"

        const val okhttpClient = "com.squareup.okhttp3:okhttp:$okhttpVersion"
        const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:$okhttpVersion"
    }

    object Coil {
        private const val coilVersion = "2.0.0-rc01"
        const val coil = "io.coil-kt:coil-compose:$coilVersion"
    }

    object LottieAnim {
        private const val lottieVersion = "4.0.0"
        const val lottieAnim = "com.airbnb.android:lottie-compose:$lottieVersion"
    }

}