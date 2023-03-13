package com.weatherapi.home_screen.models

import androidx.compose.runtime.Immutable
import com.weatherapi.core.view_state.ViewState

@Immutable
internal data class HomeScreenViewState(
    val viewState: ViewState<WeatherForecastInfoUiModel>

) {
    companion object {
        fun initial() = HomeScreenViewState(viewState = ViewState.Loading)
    }
}