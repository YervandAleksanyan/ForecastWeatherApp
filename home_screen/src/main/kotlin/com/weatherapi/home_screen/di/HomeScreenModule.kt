package com.weatherapi.home_screen.di

import com.weatherapi.home_screen.mappers.di.uiMapperModule
import com.weatherapi.home_screen.viewmodels.di.viewModelModules

val homeScreenModule = viewModelModules + uiMapperModule