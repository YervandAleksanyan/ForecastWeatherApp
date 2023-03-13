package com.weatherapi.domain.models


data class WeatherDomainModel(
    val location: LocationDomainModel,
    val currentData: CurrentDomainModel,
    val forecastInfo: ForecastInfoDomainModel
)

data class LocationDomainModel(
    val name: String,
    val country: String,
    val currentTimestamp: Long,
    val localTime: String,
)

data class CurrentDomainModel(
    val currentTimestamp: Long,
    val currentDateTime: String,
    val currentTempByCelsius: Double,
    val currentTempByFahrenheit: Double,
    val condition: ConditionDomainModel,
    val windSpeedByMph: Double,
    val windSpeedByKph: Double,
    val humidity: Int
)

data class ConditionDomainModel(
    val weatherInfo: String,
    val weatherInfoImgUrl: String
)

data class ForecastInfoDomainModel(
    val forecastDayListInfo: List<ForecastDayInfoDomainModel>
)


data class ForecastDayInfoDomainModel(
    val dateInfo: String,
    val timestamp: Long,
    val itemDayInfo: ItemDateInfoDomainModel
)

data class ItemDateInfoDomainModel(
    val maxTempByCelsius: Double,
    val maxTempByF: Double,
    val minTemByCelsius: Double,
    val minTempByF: Double,
    val condition: ConditionDomainModel
)