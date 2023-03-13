package com.weatherapi.domain.repository

import com.weatherapi.domain.models.SearchCityDomainModel
import com.weatherapi.domain.models.WeatherDomainModel
import com.weatherapi.domain.util.RequestResult

interface ForecastInfoRepository {

    suspend fun getForecastDataByLocationName(
        locationName: String,
        countDay: Int
    ): RequestResult<WeatherDomainModel>

    suspend fun searchCity(city: String): RequestResult<List<SearchCityDomainModel>>
}