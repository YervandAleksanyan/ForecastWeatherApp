package com.weatherapi.core.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.weatherapi.core.R


val typography = WeatherForecastAppTypography(
    titleLarge = TextStyle(
        fontSize = 56.sp,
        lineHeight = 66.83.sp,
        fontFamily = FontFamily(Font(R.font.sf_pro_bold)),
        fontWeight = FontWeight.W700,
    ),
    titleLargeSmallWeight = TextStyle(
        fontSize = 56.sp,
        lineHeight = 66.83.sp,
        fontFamily = FontFamily(Font(R.font.sf_pro_thin)),
        fontWeight = FontWeight(110),
    ),
    titleMedium = TextStyle(
        fontSize = 32.sp,
        lineHeight = 38.19.sp,
        fontFamily = FontFamily(Font(R.font.sf_pro_bold)),
        fontWeight = FontWeight.W700,
    ),
    titleSmall = TextStyle(
        fontSize = 16.sp,
        lineHeight = 19.09.sp,
        fontFamily = FontFamily(Font(R.font.sf_pro_semibold)),
        fontWeight = FontWeight(590)
    ),

    labelLargeBold = TextStyle(
        fontSize = 16.sp,
        lineHeight = 24.sp,
        fontFamily = FontFamily(Font(R.font.sf_pro_bold)),
        fontWeight = FontWeight.W700
    ),
    labelLargeMedium = TextStyle(
        fontSize = 16.sp,
        lineHeight = 24.sp,
        fontFamily = FontFamily(Font(R.font.sf_pro_medium)),
        fontWeight = FontWeight(510)
    ),

    labelLargeRegular = TextStyle(
        fontSize = 16.sp,
        lineHeight = 19.09.sp,
        fontFamily = FontFamily(Font(R.font.sf_pro_regular)),
        fontWeight = FontWeight.W400
    ),

    labelMediumBold = TextStyle(
        fontSize = 12.sp,
        lineHeight = 14.32.sp,
        fontFamily = FontFamily(Font(R.font.sf_pro_bold)),
        fontWeight = FontWeight.W700
    ),
    labelMediumRegular = TextStyle(
        fontSize = 12.sp,
        lineHeight = 14.32.sp,
        fontFamily = FontFamily(Font(R.font.sf_pro_regular)),
        fontWeight = FontWeight.W400
    ),
    textFieldMedium = TextStyle(
        fontSize = 16.sp,
        lineHeight = 19.09.sp,
        fontFamily = FontFamily(Font(R.font.sf_pro_medium)),
        fontWeight = FontWeight(510)
    ),
    textFieldRegular = TextStyle(
        fontSize = 16.sp,
        lineHeight = 19.09.sp,
        fontFamily = FontFamily(Font(R.font.sf_pro_regular)),
        fontWeight = FontWeight.W400
    )
)