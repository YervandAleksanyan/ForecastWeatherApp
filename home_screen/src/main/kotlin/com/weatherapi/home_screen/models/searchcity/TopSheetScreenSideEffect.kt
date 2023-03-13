package com.weatherapi.home_screen.models.searchcity

import androidx.compose.runtime.Immutable

@Immutable
sealed class TopSheetScreenSideEffect {
    @Immutable
    data class CloseSearchView(
        val selectData: SearchCityItemUIModel? = null,
        val itemClicked: Boolean
    ) : TopSheetScreenSideEffect()
}