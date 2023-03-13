package com.weatherapi.data.forecast_info.repository

import com.weatherapi.data.forecast_info.datasource.remote.ForecastInfoRemoteDataSource
import com.weatherapi.domain.models.SearchCityDomainModel
import com.weatherapi.domain.models.WeatherDomainModel
import com.weatherapi.domain.repository.ForecastInfoRepository
import com.weatherapi.domain.util.RequestResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

internal class ForecastInfoRepositoryImpl(
    private val forecastInfoRemoteDataSource: ForecastInfoRemoteDataSource,
    private val coroutineDispatcher: CoroutineDispatcher
) : ForecastInfoRepository {

    override suspend fun getForecastDataByLocationName(
        locationName: String,
        countDay: Int
    ): RequestResult<WeatherDomainModel> = withContext(context = coroutineDispatcher) {
        forecastInfoRemoteDataSource.getForecastDataByLocationName(
            locationName = locationName, countDay = countDay
        )
    }

    override suspend fun searchCity(city: String): RequestResult<List<SearchCityDomainModel>> =
        withContext(context = coroutineDispatcher) {
            forecastInfoRemoteDataSource.searchCity(city)
        }
}