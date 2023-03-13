package com.weatherapi.weatherforecast.nav_graph

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.weatherapi.core.navigation.AppRoutes
import com.weatherapi.home_screen.presentation.HomeScreen
import com.weatherapi.splash_screen.ui.AnimatedSplashScreen

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AppNavGraph(
    navController: NavHostController
) {
    AnimatedNavHost(
        navController = navController,
        startDestination = AppRoutes.SplashScreen.route
    ) {
        composable(route = AppRoutes.SplashScreen.route) {
            AnimatedSplashScreen(navController = navController)
        }
        composable(route = AppRoutes.WeatherHomeScreen.route,
            enterTransition = {
                fadeIn(animationSpec = tween(500))
            },
            exitTransition = {
                slideOutHorizontally(animationSpec = tween(500)) { -1100 }
            },
            popEnterTransition = {
                slideInHorizontally(animationSpec = tween(500)) { -1100 }
            },
            popExitTransition = {
                slideOutHorizontally(animationSpec = tween(500)) { 1100 }
            }
        ) {
            HomeScreen(modifier = Modifier.fillMaxSize())
        }
    }
}