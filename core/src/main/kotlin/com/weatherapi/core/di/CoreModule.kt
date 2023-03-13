package com.weatherapi.core.di

import com.weatherapi.core.locationprovider.di.locationModule

val coreModules = coroutineModule + locationModule