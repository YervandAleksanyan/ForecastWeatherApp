package com.weatherapi.home_screen.viewmodels.di

import com.weatherapi.home_screen.viewmodels.HomeScreenViewModel
import com.weatherapi.home_screen.viewmodels.TopSheetScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val viewModelModules = module {
    viewModel {
        HomeScreenViewModel(
            getForecastInfoDataUseCase = get(),
            locationProviderClient = get(),
            uiModelMapper = get()
        )
    }
    viewModel {
        TopSheetScreenViewModel(
            searchCityUseCase = get(),
            uiModelMapper = get()
        )
    }
}