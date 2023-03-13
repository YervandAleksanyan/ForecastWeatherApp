package com.weatherapi.domain.usecases

import com.weatherapi.domain.models.SendInfoRequestModel
import com.weatherapi.domain.models.WeatherDomainModel
import com.weatherapi.domain.repository.ForecastInfoRepository
import com.weatherapi.domain.util.RequestResult
import com.weatherapi.domain.util.UseCase

class GetForecastInfoDataUseCase(
    private val forecastInfoRepository: ForecastInfoRepository
) : UseCase<SendInfoRequestModel, RequestResult<WeatherDomainModel>>() {

    override suspend fun execute(param: SendInfoRequestModel): RequestResult<WeatherDomainModel> =
        forecastInfoRepository.getForecastDataByLocationName(
            locationName = param.locationName,
            countDay = param.countDay
        )
}