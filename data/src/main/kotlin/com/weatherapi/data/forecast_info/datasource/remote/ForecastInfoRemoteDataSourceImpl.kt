package com.weatherapi.data.forecast_info.datasource.remote

import com.weatherapi.data.BuildConfig
import com.weatherapi.data.api.WeatherAppRestServiceApi
import com.weatherapi.domain.models.SearchCityDomainModel
import com.weatherapi.domain.models.WeatherDomainModel
import com.weatherapi.domain.util.RequestResult

internal class ForecastInfoRemoteDataSourceImpl(
    private val weatherApi: WeatherAppRestServiceApi
) : ForecastInfoRemoteDataSource {

    companion object {
        private const val API_KEY = BuildConfig.API_KEY
    }

    override suspend fun getForecastDataByLocationName(
        locationName: String,
        countDay: Int
    ): RequestResult<WeatherDomainModel> =
        try {
            RequestResult.Success(
                data = weatherApi.getCurrentWeatherDataByLocationName(
                    apiKey = API_KEY,
                    locationName = locationName,
                    countDay = countDay
                ).map()
            )
        } catch (exception: Exception) {
            RequestResult.Error(exception)
        }

    override suspend fun searchCity(city: String): RequestResult<List<SearchCityDomainModel>> =
        try {
            RequestResult.Success(
                data =
                weatherApi.searchCityByName(apiKey = API_KEY, name = city)?.map { it.map() }
                    ?: emptyList()
            )
        } catch (exception: Exception) {
            RequestResult.Error(exception)
        }
}