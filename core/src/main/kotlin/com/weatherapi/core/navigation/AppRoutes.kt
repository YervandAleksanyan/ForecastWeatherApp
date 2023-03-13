package com.weatherapi.core.navigation

sealed class AppRoutes(val route: String) {
    object SplashScreen : AppRoutes(route = "Splash Screen")
    object WeatherHomeScreen : AppRoutes(route = "Weather Home Screen")
}
