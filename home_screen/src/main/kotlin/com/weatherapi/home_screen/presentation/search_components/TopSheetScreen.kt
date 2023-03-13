package com.weatherapi.home_screen.presentation.search_components

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.weatherapi.core.R
import com.weatherapi.core.extensions.clickableSingle
import com.weatherapi.core.theme.WeatherForecastAppExerciseTheme
import com.weatherapi.core.theme.WeatherForecastAppTheme
import com.weatherapi.core.view_state.ViewState
import com.weatherapi.home_screen.MAX_TEXT_FIELD_CHAR
import com.weatherapi.home_screen.models.searchcity.SearchCityItemUIModel
import com.weatherapi.home_screen.models.searchcity.TopSheetScreenSideEffect
import com.weatherapi.home_screen.viewmodels.TopSheetScreenViewModel
import org.koin.androidx.compose.viewModel
import org.orbitmvi.orbit.compose.collectSideEffect


@Composable
internal fun TopSheetScreen(
    modifier: Modifier,
    onCloseTopSheetScreenCallBack: (() -> Unit)? = null,
    onItemClickedCallBack: ((SearchCityItemUIModel) -> Unit)? = null
) {
    val topSheetScreenViewModel: TopSheetScreenViewModel by viewModel()
    var cityData by rememberSaveable {
        mutableStateOf(value = emptyList<SearchCityItemUIModel>())
    }
    val topSheetScreenViewState by topSheetScreenViewModel.container.stateFlow.collectAsState()
    val hintText = stringResource(id = R.string.search_city_hint)
    var textFieldValue by rememberSaveable {
        mutableStateOf("")
    }
    var isShowCityListData by rememberSaveable {
        mutableStateOf(false)
    }

    topSheetScreenViewModel.collectSideEffect { sideEffects ->
        when (sideEffects) {
            is TopSheetScreenSideEffect.CloseSearchView -> {
                if (sideEffects.itemClicked && sideEffects.selectData != null) {
                    onItemClickedCallBack?.invoke(sideEffects.selectData)
                } else {
                    onCloseTopSheetScreenCallBack?.invoke()
                }
            }
        }
    }

    Card(
        modifier = modifier,
        shape = WeatherForecastAppTheme.roundedCornerShape.shapeMedium,
        backgroundColor = WeatherForecastAppTheme.colors.secondaryBackground

    ) {
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.Top
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 26.dp, end = 32.dp, top = 27.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Image(
                    modifier = Modifier
                        .size(width = 24.dp, height = 24.dp)
                        .clickableSingle { topSheetScreenViewModel.closeScreen() },
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = null
                )
                Spacer(
                    modifier = Modifier
                        .width(10.dp)
                )

                BasicTextField(
                    value = textFieldValue,
                    onValueChange = { newText ->
                        if (newText.length <= MAX_TEXT_FIELD_CHAR) textFieldValue = newText
                        isShowCityListData = textFieldValue.isNotEmpty()
                        topSheetScreenViewModel.searchCity(city = textFieldValue)
                    },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Search
                    ),
                    keyboardActions = KeyboardActions(onSearch = { textFieldValue = "" }),
                    textStyle = WeatherForecastAppTheme.typography.textFieldMedium
                        .copy(color = WeatherForecastAppTheme.colors.primaryTextFieldColor),
                    cursorBrush = Brush.linearGradient(
                        listOf(
                            WeatherForecastAppTheme.colors.primaryTextFieldColor,
                            WeatherForecastAppTheme.colors.primaryTextFieldColor
                        )
                    ),
                    decorationBox = { innerTextField ->
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .defaultMinSize(minHeight = 55.dp)
                                .wrapContentHeight()
                                .border(
                                    1.dp, WeatherForecastAppTheme.colors.primaryTextFieldColor,
                                    RoundedCornerShape(15.dp)
                                )
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(
                                        start = 13.dp,
                                        top = 16.dp,
                                        bottom = 15.dp,
                                        end = 13.dp
                                    ),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                if (textFieldValue.isEmpty()) {
                                    Text(
                                        style = WeatherForecastAppTheme
                                            .typography.textFieldRegular
                                            .copy(color = WeatherForecastAppTheme.colors.primaryHintColor),
                                        text = hintText
                                    )
                                } else {
                                    innerTextField.invoke()
                                    Spacer(modifier = Modifier.width(10.dp))
                                    Image(
                                        modifier = Modifier
                                            .clickableSingle { textFieldValue = "" }
                                            .size(width = 24.dp, height = 24.dp),
                                        painter = painterResource(id = R.drawable.ic_clear),
                                        contentDescription = null
                                    )
                                }
                            }
                        }
                    }
                )
            }
            if (isShowCityListData) {
                SearchCityContainerScreen(
                    modifier = Modifier
                        .fillMaxWidth(),
                    data = cityData,
                    closeTopSheetSearchScreenCallBack = {
                        topSheetScreenViewModel.closeScreen()
                    }
                ) {
                    topSheetScreenViewModel.cityItemClicked(data = it)
                }
                when (val viewState = topSheetScreenViewState.viewState) {
                    is ViewState.Success -> {
                        cityData = viewState.data
                    }
                    is ViewState.Loading -> {}
                    is ViewState.Empty -> {}
                    is ViewState.Error -> {}
                }
            }
        }
    }
}


@Preview(name = "TopSheetLightMode", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun TopSheetScreenLightPreviewMode() {
    WeatherForecastAppExerciseTheme(darkTheme = false) {
        TopSheetScreen(
            modifier = Modifier
                .requiredSize(width = 390.dp, height = 104.dp),
        )
    }
}