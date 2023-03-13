package com.weatherapi.weatherforecast

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.*
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.weatherapi.core.theme.WeatherForecastAppExerciseTheme
import com.weatherapi.core.theme.WeatherForecastAppStyle
import com.weatherapi.weatherforecast.nav_graph.AppNavGraph

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val isDarkModeValue = isSystemInDarkTheme()
            val currentStyle by remember { mutableStateOf(WeatherForecastAppStyle.Main) }
            val isDarkMode by remember { mutableStateOf(isDarkModeValue) }
            val systemUiController = rememberSystemUiController()

            if (isDarkMode) {
                systemUiController.setSystemBarsColor(color = Color.Transparent)
            } else {
                systemUiController.setSystemBarsColor(color = Color.Black)
            }

            WeatherForecastAppExerciseTheme(
                darkTheme = isDarkMode,
                style = currentStyle
            ){
                CompositionLocalProvider {
                    val animatedNavController = rememberAnimatedNavController()
                    AppNavGraph(
                        navController = animatedNavController
                    )
                }
            }
        }
    }
}
