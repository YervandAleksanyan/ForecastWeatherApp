package com.weatherapi.weatherforecast

import android.app.Application
import com.weatherapi.core.di.coreModules
import com.weatherapi.data.di.dataModules
import com.weatherapi.domain.di.domainModules
import com.weatherapi.home_screen.di.homeScreenModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class WeatherForecastApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@WeatherForecastApp)
            modules(coreModules + domainModules + dataModules + homeScreenModule)
        }
    }
}