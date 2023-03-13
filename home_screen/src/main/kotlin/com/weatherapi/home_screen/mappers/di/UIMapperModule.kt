package com.weatherapi.home_screen.mappers.di

import com.weatherapi.home_screen.mappers.SearchCityDomainToUIMapper
import com.weatherapi.home_screen.mappers.WeatherDomainToUiModelMapper
import org.koin.dsl.module

internal val uiMapperModule = module {
    factory { WeatherDomainToUiModelMapper(context = get()) }
    factory { SearchCityDomainToUIMapper() }
}