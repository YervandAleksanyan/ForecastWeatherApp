package com.weatherapi.home_screen.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.weatherapi.home_screen.GRID_CELLS_FIX_COUNT
import com.weatherapi.home_screen.models.DayStateEnum
import com.weatherapi.home_screen.models.ForecastItemDayInfoUIModel

@Composable
internal fun WeatherDaysInfoContainer(modifier: Modifier, data: List<ForecastItemDayInfoUIModel>) {

    LazyHorizontalGrid(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(51.dp),
        rows = GridCells.Fixed(GRID_CELLS_FIX_COUNT),
    ) {
        itemsIndexed(items = data, key = { _, item -> item.id }) { index, item ->
            ForecastItemDayInfoScreen(
                modifier = Modifier
                    .wrapContentSize(),
                data = item.copy(
                    dayInfoName = when (index) {
                        0 -> {
                            stringResource(id = DayStateEnum.TODAY.dayNameStringResId)
                        }
                        1 -> {
                            stringResource(id = DayStateEnum.TOMORROW.dayNameStringResId)
                        }
                        else -> item.dayInfoName
                    }
                )
            )
        }
    }
}