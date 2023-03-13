package com.weatherapi.domain.usecases

import com.weatherapi.domain.models.SearchCityDomainModel
import com.weatherapi.domain.repository.ForecastInfoRepository
import com.weatherapi.domain.util.RequestResult
import com.weatherapi.domain.util.UseCase

class SearchCityUseCase(
    private val forecastInfoRepository: ForecastInfoRepository
) : UseCase<String, RequestResult<List<SearchCityDomainModel>>>() {

    override suspend fun execute(param: String): RequestResult<List<SearchCityDomainModel>> =
        forecastInfoRepository.searchCity(
            city = param
        )
}