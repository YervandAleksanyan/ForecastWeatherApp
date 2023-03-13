package com.weatherapi.domain.usecases.di

import com.weatherapi.domain.usecases.GetForecastInfoDataUseCase
import com.weatherapi.domain.usecases.SearchCityUseCase
import org.koin.dsl.module

internal val useCaseModule = module {
    factory { GetForecastInfoDataUseCase(forecastInfoRepository = get()) }
    factory { SearchCityUseCase(forecastInfoRepository = get()) }
}