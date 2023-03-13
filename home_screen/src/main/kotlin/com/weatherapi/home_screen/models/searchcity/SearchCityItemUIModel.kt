package com.weatherapi.home_screen.models.searchcity

import androidx.compose.runtime.Immutable

@Immutable
data class SearchCityItemUIModel(
    val id: Long,
    val name: String,
    val region: String
) {
    companion object {
        fun initial() = SearchCityItemUIModel(
            id = 12345,
            name = "Las Vegas",
            region = "Nevada"
        )
    }
}