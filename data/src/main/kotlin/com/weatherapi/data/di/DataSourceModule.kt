package com.weatherapi.data.di

import com.weatherapi.data.forecast_info.datasource.remote.ForecastInfoRemoteDataSource
import com.weatherapi.data.forecast_info.datasource.remote.ForecastInfoRemoteDataSourceImpl
import org.koin.dsl.module

internal val dataSourceModule = module {

    single<ForecastInfoRemoteDataSource> {
        ForecastInfoRemoteDataSourceImpl(weatherApi = get())
    }
}