package com.weatherapi.home_screen.presentation

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.weatherapi.core.R
import com.weatherapi.core.theme.WeatherForecastAppExerciseTheme
import com.weatherapi.core.theme.WeatherForecastAppTheme
import com.weatherapi.home_screen.models.ForecastItemDayInfoUIModel

@Composable
internal fun ForecastItemDayInfoScreen(
    modifier: Modifier,
    data: ForecastItemDayInfoUIModel,
    isPreviewMode: Boolean = false
) {
    Column(
        modifier = modifier
            .wrapContentHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (isPreviewMode) {
            Image(
                modifier = Modifier
                    .size(width = 32.dp, height = 32.dp),
                painter = painterResource(id = R.drawable.ic_day_info_preview),
                contentDescription = null
            )
        } else {
            SubcomposeAsyncImage(
                modifier = Modifier
                    .size(width = 32.dp, height = 32.dp),
                contentDescription = null,
                loading = {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .align(
                                Alignment.Center
                            ),
                        color = WeatherForecastAppTheme.colors.primaryTextFieldColor
                    )
                },
                model = data.itemDayInfo.condition.weatherInfoImgUrl
            )
        }


        Spacer(modifier = Modifier.height(6.dp))

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = data.itemDayInfo.minMaxTempByF,
            style = WeatherForecastAppTheme.typography.labelMediumRegular,
            textAlign = TextAlign.Center,
            color = WeatherForecastAppTheme.colors.primaryTextColor
        )
        Spacer(modifier = Modifier.height(6.dp))

        Text(
            modifier = Modifier.wrapContentWidth(),
            text = data.dayInfoName,
            style = WeatherForecastAppTheme.typography.labelMediumBold,
            textAlign = TextAlign.Center,
            color = WeatherForecastAppTheme.colors.primaryTextColor
        )
    }
}

@Preview(name = "ItemDayLightMode", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun ForecastItemDayInfoScreenLightPreviewMode() {
    WeatherForecastAppExerciseTheme(darkTheme = false) {
        ForecastItemDayInfoScreen(
            modifier = Modifier
                .width(63.dp)
                .wrapContentHeight(),
            data = ForecastItemDayInfoUIModel.initial(),
            isPreviewMode = true
        )
    }
}