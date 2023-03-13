package com.weatherapi.domain.models

data class SearchCityDomainModel(
    val id: Long,
    val name: String,
    val region: String,
    val country: String,
    val latitude: Double,
    val longitude: Double
)
