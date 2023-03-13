package com.weatherapi.home_screen.models.searchcity

import androidx.compose.runtime.Immutable
import com.weatherapi.core.view_state.ViewState

@Immutable
internal data class TopSheetScreenViewState(
    val viewState: ViewState<List<SearchCityItemUIModel>>
) {
    companion object {
        fun initial() = TopSheetScreenViewState(ViewState.Loading)
    }
}
