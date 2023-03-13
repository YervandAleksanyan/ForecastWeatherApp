package com.weatherapi.home_screen.models

import androidx.compose.runtime.Immutable

@Immutable
data class WeatherForecastInfoUiModel(
    val location: LocationUIModel,
    val currentData: CurrentUIModel,
    val forecastDaysInfoData: List<ForecastItemDayInfoUIModel>
) {
    companion object {
        fun initial() = WeatherForecastInfoUiModel(
            location = LocationUIModel(
                name = "----",
                country = "----"
            ),
            currentData = CurrentUIModel(
                currentDate = "-- -- --",
                currentTime = "--:--",
                currentTempByCelsius = "--",
                currentTempByFahrenheit = "--",
                condition = ConditionUIModel(
                    weatherDayInfo = "--",
                    weatherInfoImgUrl = ""
                ),
                windSpeedByKph = "-- kph",
                windSpeedByMph = "-- mph",
                humidity = "-- %"
            ),
            forecastDaysInfoData = emptyList()
        )
    }
}

@Immutable
data class LocationUIModel(
    val name: String,
    val country: String
)

@Immutable
data class CurrentUIModel(
    val currentDate: String,
    val currentTime: String,
    val currentTempByCelsius: String,
    val currentTempByFahrenheit: String,
    val condition: ConditionUIModel,
    val windSpeedByMph: String,
    val windSpeedByKph: String,
    val humidity: String
)

@Immutable
data class ConditionUIModel(
    val weatherDayInfo: String,
    val weatherInfoImgUrl: String
)

@Immutable
data class ForecastItemDayInfoUIModel(
    val id: Long,
    val dayInfoName: String,
    val itemDayInfo: ItemDateInfoUIModel
) {
    companion object {
        fun initial() = ForecastItemDayInfoUIModel(
            id = 1,
            dayInfoName = "Today",
            itemDayInfo = ItemDateInfoUIModel(
                minMaxTempByF = "82.4°/86°F",
                minMaxTempByCelsius = "82.4°/86°C",
                condition = ConditionUIModel(
                    weatherInfoImgUrl = "",
                    weatherDayInfo = "Sunny day"
                )
            )
        )
    }
}

@Immutable
data class ItemDateInfoUIModel(
    val minMaxTempByCelsius: String,
    val minMaxTempByF: String,
    val condition: ConditionUIModel
)