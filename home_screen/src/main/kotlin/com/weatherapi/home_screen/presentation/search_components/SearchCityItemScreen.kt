package com.weatherapi.home_screen.presentation.search_components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.weatherapi.core.theme.WeatherForecastAppExerciseTheme
import com.weatherapi.core.theme.WeatherForecastAppTheme
import com.weatherapi.home_screen.models.searchcity.SearchCityItemUIModel
import com.weatherapi.core.extensions.clickableSingle

@Composable
internal fun SearchCityItemScreen(
    modifier: Modifier,
    data: SearchCityItemUIModel,
    onItemClickedListener: ((SearchCityItemUIModel) -> Unit)? = null
) {
    Row(modifier = modifier
        .fillMaxWidth()
        .height(24.dp)
        .clickableSingle { onItemClickedListener?.invoke(data)},
        horizontalArrangement = Arrangement.Start
    ) {
        Text(
            text = "${data.name} - ",
            style = WeatherForecastAppTheme.typography.labelLargeBold
                .copy(color = WeatherForecastAppTheme.colors.primaryTextFieldColor),
            textAlign = TextAlign.Center,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1
        )
        Text(
            text = data.region,
            style = WeatherForecastAppTheme.typography.labelLargeMedium
                .copy(color = WeatherForecastAppTheme.colors.primaryTextFieldColor),
            textAlign = TextAlign.Center,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun SearchCityItemLightModePreview() {
    WeatherForecastAppExerciseTheme(darkTheme = false) {
        SearchCityItemScreen(
            modifier = Modifier
                .background(WeatherForecastAppTheme.colors.secondaryBackground)
                .size(width = 156.dp, height = 24.dp),
            data = SearchCityItemUIModel.initial()
        )
    }
}