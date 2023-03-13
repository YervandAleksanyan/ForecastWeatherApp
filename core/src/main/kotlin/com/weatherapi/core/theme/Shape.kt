package com.weatherapi.core.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.unit.dp

val shapes = WeatherForecastAppRoundedCornerShape(
    shapeLarge = RoundedCornerShape(40.dp),
    shapeMedium = RoundedCornerShape(
        topStart = 0.dp,
        topEnd = 0.dp,
        bottomEnd = 30.dp,
        bottomStart = 30.dp
    ),
    shapeSmall = RoundedCornerShape(20.dp)
)