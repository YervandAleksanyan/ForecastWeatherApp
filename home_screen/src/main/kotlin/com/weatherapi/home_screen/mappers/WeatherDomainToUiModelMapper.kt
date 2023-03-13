package com.weatherapi.home_screen.mappers

import android.content.Context
import com.weatherapi.core.R
import com.weatherapi.core.mapper.MappableFrom
import com.weatherapi.domain.models.WeatherDomainModel
import com.weatherapi.domain.util.DateUtils.DATE_FORMAT
import com.weatherapi.domain.util.DateUtils.DAY_FORMAT
import com.weatherapi.domain.util.DateUtils.TIME_FORMAT
import com.weatherapi.domain.util.DateUtils.createISODateFormat
import com.weatherapi.home_screen.models.*
import java.util.*

internal class WeatherDomainToUiModelMapper(private val context: Context) :
    MappableFrom<WeatherDomainModel, WeatherForecastInfoUiModel> {
    companion object {
        private const val PREFIX_HTTPS = "https:"
    }

    override fun mapFrom(input: WeatherDomainModel): WeatherForecastInfoUiModel =
        WeatherForecastInfoUiModel(
            location = LocationUIModel(
                name = input.location.name,
                country = input.location.country
            ),
            currentData = CurrentUIModel(
                currentDate = createISODateFormat(
                    millis = input.currentData.currentTimestamp,
                    formatType = DATE_FORMAT
                ),
                currentTime = createISODateFormat(input.currentData.currentTimestamp, TIME_FORMAT),
                currentTempByFahrenheit = input.currentData.currentTempByCelsius.toString(),
                currentTempByCelsius = input.currentData.currentTempByCelsius.toString(),
                condition = ConditionUIModel(
                    weatherInfoImgUrl = "$PREFIX_HTTPS${input.currentData.condition.weatherInfoImgUrl}",
                    weatherDayInfo = String.format(
                        context.getString(R.string.day_info_formatter),
                        input.currentData.condition.weatherInfo.lowercase(Locale.US)
                    )
                ),
                windSpeedByMph = String.format(
                    Locale.US,
                    context.getString(R.string.speed_wind_mph_formatter),
                    input.currentData.windSpeedByMph
                ),
                windSpeedByKph = String.format(
                    Locale.US,
                    context.getString(R.string.speed_wind_kph_formatter),
                    input.currentData.windSpeedByKph
                ),
                humidity = String.format(
                    Locale.US,
                    context.getString(R.string.humidity_formatter), input.currentData.humidity
                )
            ),
            forecastDaysInfoData = input.forecastInfo.forecastDayListInfo.map {
                ForecastItemDayInfoUIModel(
                    id = it.timestamp,
                    dayInfoName = createISODateFormat(it.timestamp, DAY_FORMAT),
                    itemDayInfo = ItemDateInfoUIModel(
                        minMaxTempByCelsius = "${it.itemDayInfo.minTemByCelsius}°/${it.itemDayInfo.maxTempByCelsius}°C",
                        minMaxTempByF = "${it.itemDayInfo.minTempByF}°/${it.itemDayInfo.maxTempByF}°F",
                        condition = ConditionUIModel(
                            weatherDayInfo = it.itemDayInfo.condition.weatherInfo,
                            weatherInfoImgUrl = "$PREFIX_HTTPS${it.itemDayInfo.condition.weatherInfoImgUrl}"
                        )
                    )
                )
            }
        )
}