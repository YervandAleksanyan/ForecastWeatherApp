package com.weatherapi.home_screen.presentation.search_components

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.weatherapi.core.R
import com.weatherapi.core.extensions.clickableSingle
import com.weatherapi.core.theme.WeatherForecastAppExerciseTheme
import com.weatherapi.core.theme.WeatherForecastAppTheme
import com.weatherapi.home_screen.GRID_CELLS_FIX_COUNT
import com.weatherapi.home_screen.models.searchcity.SearchCityItemUIModel

@Composable
internal fun SearchCityContainerScreen(
    modifier: Modifier,
    data: List<SearchCityItemUIModel>,
    closeTopSheetSearchScreenCallBack: (() -> Unit)? = null,
    onItemClickedListener: ((SearchCityItemUIModel) -> Unit)? = null
) {
    Column(modifier = modifier) {
        LazyVerticalGrid(
            modifier = modifier
                .heightIn(min = 100.dp, max = 230.dp)
                .padding(start = 32.dp, end = 32.dp, top = 39.dp, bottom = 24.dp),
            columns = GridCells.Fixed(GRID_CELLS_FIX_COUNT),
            verticalArrangement = Arrangement.spacedBy(25.dp),
            horizontalArrangement = Arrangement.Start
        ) {
            items(items = data, key = { it.id }) { item ->
                SearchCityItemScreen(
                    modifier = Modifier.wrapContentSize(),
                    data = item,
                    onItemClickedListener = onItemClickedListener
                )
            }
        }

        Card(
            modifier = modifier
                .requiredHeight(38.dp)
                .clickableSingle { closeTopSheetSearchScreenCallBack?.invoke() },
            shape = WeatherForecastAppTheme.roundedCornerShape.shapeMedium,
            backgroundColor = WeatherForecastAppTheme.colors.thirdBackground
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    modifier = Modifier
                        .size(width = 12.73.dp, height = 7.78.dp),
                    painter = painterResource(id = R.drawable.ic_up),
                    contentDescription = null
                )
            }
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun SearchCityContainerLightPreviewMode() {
    WeatherForecastAppExerciseTheme(darkTheme = false) {
        val data = mutableListOf<SearchCityItemUIModel>()
        repeat(5) {
            data.add(SearchCityItemUIModel.initial().copy(id = it.toLong()))
        }
        SearchCityContainerScreen(
            modifier = Modifier
                .fillMaxSize()
                .background(WeatherForecastAppTheme.colors.secondaryBackground),
            data = data
        )
    }
}