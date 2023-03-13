package com.weatherapi.data.di

import com.weatherapi.core.di.DispatchersName
import com.weatherapi.data.forecast_info.repository.ForecastInfoRepositoryImpl
import com.weatherapi.domain.repository.ForecastInfoRepository
import org.koin.core.qualifier.named
import org.koin.dsl.module

internal val repositoryModule = module {

    single<ForecastInfoRepository> {
        ForecastInfoRepositoryImpl(
            coroutineDispatcher = get(qualifier = named(DispatchersName.IO)),
            forecastInfoRemoteDataSource = get()
        )
    }
}