package com.weatherapi.core.locationprovider.di

import com.weatherapi.core.locationprovider.LocationProviderClient
import org.koin.dsl.module

internal val locationModule = module {
    single { LocationProviderClient(context = get()) }
}