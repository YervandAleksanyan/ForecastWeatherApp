package com.weatherapi.domain.models

private const val DEFAULT_COUNT_DAY_INFO = 3

data class SendInfoRequestModel(
    val countDay: Int = DEFAULT_COUNT_DAY_INFO,
    val locationName: String
)
