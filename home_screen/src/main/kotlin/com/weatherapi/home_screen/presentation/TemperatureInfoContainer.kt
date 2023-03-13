package com.weatherapi.home_screen.presentation

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.SubcomposeAsyncImage
import com.weatherapi.core.theme.WeatherForecastAppTheme
import com.weatherapi.home_screen.models.WeatherForecastInfoUiModel

@Composable
fun TemperatureInfoContainer(
    modifier: Modifier,
    weatherForecastInfoData: WeatherForecastInfoUiModel
) {

    val icWindSpeedInfoRes = painterResource(id = com.weatherapi.core.R.drawable.ic_wind)
    val icHumidityInfoRes = painterResource(id = com.weatherapi.core.R.drawable.ic_droplet)
    val tempFahrenheitRes = stringResource(id = com.weatherapi.core.R.string.temp_fahrenheit_symbol)

    ConstraintLayout(
        modifier = modifier
            .wrapContentWidth()
    ) {
        val (icWeatherState, tempInfo, tempTypeInfo, dayStateInfo,
            icWindSpeed, windSpeedInfo, icHumidity, humidityInfo) = createRefs()
        createHorizontalChain(tempInfo, tempTypeInfo, chainStyle = ChainStyle.Packed)
        SubcomposeAsyncImage(
            modifier = Modifier
                .size(height = 80.dp, width = 80.dp)
                .constrainAs(icWeatherState) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                },
            model = weatherForecastInfoData.currentData.condition.weatherInfoImgUrl,
            contentScale = ContentScale.FillBounds,
            contentDescription = null,
            loading = {
                CircularProgressIndicator(
                    modifier = Modifier
                        .align(
                            Alignment.Center
                        ),
                    color = WeatherForecastAppTheme.colors.primaryTextFieldColor
                )
            }
        )

        Text(
            modifier = Modifier
                .constrainAs(tempInfo) {
                    start.linkTo(parent.start)
                    end.linkTo(tempTypeInfo.start)
                    top.linkTo(icWeatherState.bottom, 18.dp)
                },
            text = weatherForecastInfoData.currentData.currentTempByFahrenheit,
            style = WeatherForecastAppTheme.typography.titleLarge,
            color = WeatherForecastAppTheme.colors.primaryTextColor
        )
        Text(
            modifier = Modifier
                .constrainAs(tempTypeInfo) {
                    linkTo(
                        top = tempInfo.top,
                        bottom = tempInfo.bottom,
                        start = tempInfo.end,
                        end = parent.end
                    )
                },
            text = tempFahrenheitRes,
            style = WeatherForecastAppTheme.typography.titleLargeSmallWeight,
            color = WeatherForecastAppTheme.colors.primaryTextColor,
        )

        Text(
            modifier = Modifier
                .constrainAs(dayStateInfo) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(tempInfo.bottom, 18.dp)
                },
            text = weatherForecastInfoData.currentData.condition.weatherDayInfo,
            style = WeatherForecastAppTheme.typography.labelLargeRegular,
            color = WeatherForecastAppTheme.colors.primaryTextColor,
        )

        Icon(
            modifier = Modifier
                .size(width = 20.dp, height = 20.dp)
                .constrainAs(icWindSpeed) {
                    start.linkTo(parent.start, 5.dp)
                    top.linkTo(dayStateInfo.bottom, 18.dp)
                },
            painter = icWindSpeedInfoRes,
            contentDescription = null,
            tint = WeatherForecastAppTheme.colors.tintColor
        )

        Text(
            modifier = Modifier
                .constrainAs(windSpeedInfo) {
                    start.linkTo(icWindSpeed.end, 8.dp)
                    top.linkTo(icWindSpeed.top)
                    bottom.linkTo(icWindSpeed.bottom)
                }
                .alpha(0.9f),
            text = weatherForecastInfoData.currentData.windSpeedByMph,
            style = WeatherForecastAppTheme.typography.labelMediumRegular,
            color = WeatherForecastAppTheme.colors.secondaryTextColor,
        )

        Icon(
            modifier = Modifier
                .size(width = 20.dp, height = 20.dp)
                .constrainAs(icHumidity) {
                    start.linkTo(windSpeedInfo.end, 40.dp)
                    top.linkTo(dayStateInfo.bottom, 18.dp)
                    end.linkTo(humidityInfo.start, 8.dp)
                },
            painter = icHumidityInfoRes,
            contentDescription = null,
            tint = WeatherForecastAppTheme.colors.tintColor
        )

        Text(
            modifier = Modifier
                .constrainAs(humidityInfo) {
                    end.linkTo(parent.end, 5.dp)
                    top.linkTo(icHumidity.top)
                    start.linkTo(icHumidity.end)
                    bottom.linkTo(icHumidity.bottom)
                }
                .alpha(0.9f),
            text = weatherForecastInfoData.currentData.humidity,
            style = WeatherForecastAppTheme.typography.labelMediumRegular,
            color = WeatherForecastAppTheme.colors.secondaryTextColor,
        )
    }
}