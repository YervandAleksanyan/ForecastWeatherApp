package com.weatherapi.data.forecast_info.models

import com.google.gson.annotations.SerializedName
import com.weatherapi.core.mapper.Mappable
import com.weatherapi.domain.models.SearchCityDomainModel

data class SearchCityResponseModel(
    @SerializedName("id")
    val id: Long?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("region")
    val region: String?,
    @SerializedName("country")
    val country: String?,
    @SerializedName("lat")
    val latitude: Double?,
    @SerializedName("lon")
    val longitude: Double?
) : Mappable<SearchCityDomainModel> {
    override fun map(): SearchCityDomainModel = SearchCityDomainModel(
        id = id ?: 0,
        name = name.orEmpty(),
        region = region.orEmpty(),
        country = country.orEmpty(),
        latitude = latitude ?: 0.0,
        longitude = longitude ?: 0.0
    )
}
