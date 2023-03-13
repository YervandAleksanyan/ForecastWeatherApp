package com.weatherapi.data.forecast_info.models

import com.google.gson.annotations.SerializedName
import com.weatherapi.core.mapper.Mappable
import com.weatherapi.domain.models.*

internal data class WeatherResponseModel(
    @SerializedName("location")
    val location: LocationResponseModel,
    @SerializedName("current")
    val currentData: CurrentResponseModel,
    @SerializedName("forecast")
    val forecastInfo: ForecastInfoResponseModel
) : Mappable<WeatherDomainModel> {
    override fun map(): WeatherDomainModel =
        WeatherDomainModel(
            location = LocationDomainModel(
                name = location.name.orEmpty(),
                country = location.country.orEmpty(),
                currentTimestamp = location.currentTimestamp ?: 0,
                localTime = location.localTime.orEmpty(),
            ),
            currentData = CurrentDomainModel(
                currentTimestamp = currentData.currentTimestamp ?: 0,
                currentDateTime = currentData.currentDateTime.orEmpty(),
                currentTempByCelsius = currentData.currentTempByCelsius ?: 0.0,
                currentTempByFahrenheit = currentData.currentTempByFahrenheit ?: 0.0,
                condition = ConditionDomainModel(
                    weatherInfo = currentData.condition?.weatherInfo.orEmpty(),
                    weatherInfoImgUrl = currentData.condition?.weatherInfoImgUrl.orEmpty()
                ),
                windSpeedByKph = currentData.windSpeedByKph ?: 0.0,
                windSpeedByMph = currentData.windSpeedByMph ?: 0.0,
                humidity = currentData.humidity ?: 0
            ),
            forecastInfo = ForecastInfoDomainModel(
                forecastDayListInfo = forecastInfo.forecastDayListInfo?.map {
                    ForecastDayInfoDomainModel(
                        dateInfo = it.dateInfo.orEmpty(),
                        timestamp = it.timestamp ?: 0,
                        itemDayInfo = ItemDateInfoDomainModel(
                            maxTempByCelsius = it.itemDayInfo?.maxTempByCelsius ?: 0.0,
                            maxTempByF = it.itemDayInfo?.maxTempByF ?: 0.0,
                            minTemByCelsius = it.itemDayInfo?.minTemByCelsius ?: 0.0,
                            minTempByF = it.itemDayInfo?.minTempByF ?: 0.0,
                            condition = ConditionDomainModel(
                                weatherInfo = it.itemDayInfo?.condition?.weatherInfo.orEmpty(),
                                weatherInfoImgUrl = it.itemDayInfo?.condition?.weatherInfoImgUrl.orEmpty()
                            )
                        )
                    )
                } ?: emptyList()
            )
        )
}

internal data class LocationResponseModel(
    @SerializedName("name")
    val name: String?,
    @SerializedName("country")
    val country: String?,
    @SerializedName("localtime_epoch")
    val currentTimestamp: Long?,
    @SerializedName("localtime")
    val localTime: String?
)

internal data class CurrentResponseModel(
    @SerializedName("last_updated_epoch")
    val currentTimestamp: Long?,
    @SerializedName("last_updated")
    val currentDateTime: String?,
    @SerializedName("temp_c")
    val currentTempByCelsius: Double?,
    @SerializedName("temp_f")
    val currentTempByFahrenheit: Double?,
    @SerializedName("condition")
    val condition: ConditionResponseModel?,
    @SerializedName("wind_mph")
    val windSpeedByMph: Double?,
    @SerializedName("wind_kph")
    val windSpeedByKph: Double?,
    @SerializedName("humidity")
    val humidity: Int?
)

internal data class ConditionResponseModel(
    @SerializedName("text")
    val weatherInfo: String?,
    @SerializedName("icon")
    val weatherInfoImgUrl: String?
)

internal data class ForecastInfoResponseModel(
    @SerializedName("forecastday")
    val forecastDayListInfo: List<ForecastDayInfoResponseModel>?
)


internal data class ForecastDayInfoResponseModel(
    @SerializedName("date")
    val dateInfo: String?,
    @SerializedName("date_epoch")
    val timestamp: Long?,
    @SerializedName("day")
    val itemDayInfo: ItemDateInfoResponseModel?
)

internal data class ItemDateInfoResponseModel(
    @SerializedName("maxtemp_c")
    val maxTempByCelsius: Double?,
    @SerializedName("maxtemp_f")
    val maxTempByF: Double?,
    @SerializedName("mintemp_c")
    val minTemByCelsius: Double?,
    @SerializedName("mintemp_f")
    val minTempByF: Double?,
    @SerializedName("condition")
    val condition: ConditionResponseModel?
)

