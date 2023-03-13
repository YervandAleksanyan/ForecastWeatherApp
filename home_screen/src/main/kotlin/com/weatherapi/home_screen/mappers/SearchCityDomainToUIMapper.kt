package com.weatherapi.home_screen.mappers

import com.weatherapi.core.mapper.MappableFrom
import com.weatherapi.domain.models.SearchCityDomainModel
import com.weatherapi.home_screen.models.searchcity.SearchCityItemUIModel
import kotlin.random.Random

internal class SearchCityDomainToUIMapper :
    MappableFrom<List<SearchCityDomainModel>, List<SearchCityItemUIModel>> {

    override fun mapFrom(input: List<SearchCityDomainModel>): List<SearchCityItemUIModel> =
        input.map {
            SearchCityItemUIModel(
                id = Random.nextInt(0, 1342) * it.id,
                name = it.name,
                region = it.region
            )
        }

}