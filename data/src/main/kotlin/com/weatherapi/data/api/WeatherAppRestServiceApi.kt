package com.weatherapi.data.api

import com.weatherapi.data.api.ApiPath.GET_FORECAST_DATA
import com.weatherapi.data.api.ApiPath.GET_SEARCH_CITY_DATA
import com.weatherapi.data.forecast_info.models.SearchCityResponseModel
import com.weatherapi.data.forecast_info.models.WeatherResponseModel
import retrofit2.http.GET
import retrofit2.http.Query

internal interface WeatherAppRestServiceApi {

    @GET(GET_FORECAST_DATA)
    suspend fun getCurrentWeatherDataByLocationName(
        @Query("key") apiKey: String,
        @Query("q") locationName: String,
        @Query("days") countDay: Int
    ): WeatherResponseModel

    @GET(GET_SEARCH_CITY_DATA)
    suspend fun searchCityByName(
        @Query("key") apiKey: String,
        @Query("q") name: String
    ): List<SearchCityResponseModel>?
}