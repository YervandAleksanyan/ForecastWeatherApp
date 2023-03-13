package com.weatherapi.core.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider



@Composable
fun WeatherForecastAppExerciseTheme(
    style: WeatherForecastAppStyle = WeatherForecastAppStyle.Main,
    darkTheme: Boolean,
    content: @Composable () -> Unit
) {
    val colors = when (darkTheme) {
        true -> {
            when (style) {
                WeatherForecastAppStyle.Main -> baseDarkPalette
            }
        }
        false -> {
            when (style) {
                WeatherForecastAppStyle.Main -> baseLightPalette
            }
        }
    }

    CompositionLocalProvider(
        LocalWeatherForecastAppColors provides colors,
        LocalWeatherForecastAppTypography provides typography,
        LocalWeatherForecastAppRoundedCornerShape provides shapes,
        content = content
    )
}