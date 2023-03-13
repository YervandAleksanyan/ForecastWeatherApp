package com.weatherapi.core.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle

data class WeatherForecastAppColors(
    val primaryBackground: Color,
    val secondaryBackground: Color,
    val thirdBackground:Color,
    val primaryTextColor: Color,
   val  secondaryTextColor:Color,
    val textStrokeColor: Color,
    val primaryTextFieldColor: Color,
    val primaryHintColor: Color,
    val tintColor:Color
)

data class WeatherForecastAppTypography(
    val titleLarge: TextStyle,
    val titleLargeSmallWeight: TextStyle,
    val titleMedium: TextStyle,
    val titleSmall: TextStyle,
    val labelLargeBold: TextStyle,
    val labelLargeMedium: TextStyle,
    val labelLargeRegular: TextStyle,
    val labelMediumBold: TextStyle,
    val labelMediumRegular: TextStyle,
    val textFieldMedium:TextStyle,
    val textFieldRegular:TextStyle
)

data class WeatherForecastAppRoundedCornerShape(
    val shapeLarge: RoundedCornerShape,
    val shapeMedium: RoundedCornerShape,
    val shapeSmall: RoundedCornerShape
)

object WeatherForecastAppTheme {
    val colors: WeatherForecastAppColors
        @Composable
        get() = LocalWeatherForecastAppColors.current

    val typography: WeatherForecastAppTypography
        @Composable
        get() = LocalWeatherForecastAppTypography.current

    val roundedCornerShape: WeatherForecastAppRoundedCornerShape
        @Composable
        get() = LocalWeatherForecastAppRoundedCornerShape.current
}

val LocalWeatherForecastAppColors = staticCompositionLocalOf<WeatherForecastAppColors> {
    error("No colors provided")
}
val LocalWeatherForecastAppTypography = staticCompositionLocalOf<WeatherForecastAppTypography> {
    error("No font provided")
}

val LocalWeatherForecastAppRoundedCornerShape =
    staticCompositionLocalOf<WeatherForecastAppRoundedCornerShape> {
        error("No shape provided")
    }

enum class WeatherForecastAppStyle {
    Main
}

