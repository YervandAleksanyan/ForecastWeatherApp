package com.weatherapi.data.forecast_info.datasource.remote

import com.weatherapi.domain.models.SearchCityDomainModel
import com.weatherapi.domain.models.WeatherDomainModel
import com.weatherapi.domain.util.RequestResult

internal interface ForecastInfoRemoteDataSource {

    suspend fun getForecastDataByLocationName(
        locationName: String,
        countDay: Int
    ): RequestResult<WeatherDomainModel>

    suspend fun searchCity(city: String): RequestResult<List<SearchCityDomainModel>>
}