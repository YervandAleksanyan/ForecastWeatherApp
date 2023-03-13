package com.weatherapi.home_screen.presentation

import android.Manifest
import android.annotation.SuppressLint
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.Icon
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.weatherapi.core.R
import com.weatherapi.core.extensions.clickableSingle
import com.weatherapi.core.theme.WeatherForecastAppTheme
import com.weatherapi.core.view_state.ViewState
import com.weatherapi.home_screen.models.WeatherForecastInfoUiModel
import com.weatherapi.home_screen.presentation.search_components.TopSheetScreen
import com.weatherapi.home_screen.viewmodels.HomeScreenViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.compose.viewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    modifier: Modifier
) {
    val homeViewModel: HomeScreenViewModel by viewModel()
    val homeScreenViewState by homeViewModel.container.stateFlow.collectAsState()
    val emptyDaysInfoMessage = stringResource(id = R.string.empty_days_data)
    val scaffoldState = rememberScaffoldState()
    val backgroundImgRes = painterResource(id = R.mipmap.home_background)
    val icSearchRes = painterResource(id = R.drawable.ic_search)
    val scope = rememberCoroutineScope()
    var isShowTopSearchCityScreen by rememberSaveable {
        mutableStateOf(false)
    }
    var weatherForecastInfoUiModel by remember {
        mutableStateOf(WeatherForecastInfoUiModel.initial())
    }

    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.RequestMultiplePermissions()) { grantedPermission ->

            for (entry in grantedPermission.entries) {
                when (entry.key) {
                    Manifest.permission.ACCESS_FINE_LOCATION -> {
                        if (entry.value) {
                            homeViewModel.requestCurrentLocation()
                        } else {
                            homeViewModel.requestWeatherData()
                        }
                    }
                }
            }
        }

    LaunchedEffect(key1 = Unit) {
        launcher.launch(
            getPermissionsArray()
        )
    }


    Scaffold(
        modifier = modifier,
        scaffoldState = scaffoldState
    ) {

        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
            val (backgroundImg, backgroundTint, progressIndicator, watchInfo,
                searchIcon, locationInfo, dateOfTime, tempInfoContainer,
                weatherDaysInfoContainer, topSheetSearchCountry) = createRefs()

            Image(
                modifier = modifier
                    .constrainAs(backgroundImg) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    },
                painter = backgroundImgRes,
                contentDescription = null,
                contentScale = ContentScale.FillBounds
            )

            Box(
                modifier = modifier
                    .constrainAs(backgroundTint) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
                    .background(color = WeatherForecastAppTheme.colors.primaryBackground)
            )

            Icon(
                modifier = Modifier
                    .requiredWidth(24.dp)
                    .requiredHeight(24.dp)
                    .constrainAs(searchIcon) {
                        end.linkTo(parent.end, 32.dp)
                        top.linkTo(parent.top, 40.dp)
                    }
                    .clickableSingle { isShowTopSearchCityScreen = true },
                painter = icSearchRes,
                tint = WeatherForecastAppTheme.colors.tintColor,
                contentDescription = null,
            )

            Text(
                modifier = Modifier
                    .constrainAs(watchInfo) {
                        linkTo(
                            top = searchIcon.top,
                            bottom = searchIcon.bottom,
                            start = parent.start,
                            end = parent.end
                        )
                    },
                style = WeatherForecastAppTheme.typography.titleSmall,
                color = WeatherForecastAppTheme.colors.primaryTextColor,
                text = weatherForecastInfoUiModel.currentData.currentTime
            )


            Text(
                modifier = Modifier
                    .constrainAs(locationInfo) {
                        top.linkTo(searchIcon.bottom, 64.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                text = weatherForecastInfoUiModel.location.name,
                style = WeatherForecastAppTheme.typography.titleMedium,
                color = WeatherForecastAppTheme.colors.primaryTextColor
            )

            Text(
                modifier = Modifier
                    .constrainAs(dateOfTime) {
                        top.linkTo(locationInfo.bottom, margin = 4.dp)
                        start.linkTo(locationInfo.start)
                        end.linkTo(locationInfo.end)
                    },
                text = weatherForecastInfoUiModel.currentData.currentDate,
                style = WeatherForecastAppTheme.typography.labelLargeRegular,
                color = WeatherForecastAppTheme.colors.primaryTextColor
            )

            TemperatureInfoContainer(
                modifier = Modifier
                    .constrainAs(ref = tempInfoContainer) {
                        top.linkTo(dateOfTime.bottom, 96.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                weatherForecastInfoData = weatherForecastInfoUiModel
            )

            WeatherDaysInfoContainer(
                modifier = Modifier
                    .requiredHeightIn(min = 72.dp, max = 80.dp)
                    .constrainAs(ref = weatherDaysInfoContainer) {
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        top.linkTo(tempInfoContainer.bottom)
                        end.linkTo(parent.end)
                    },
                data = weatherForecastInfoUiModel.forecastDaysInfoData
            )
            if (isShowTopSearchCityScreen) {
                TopSheetScreen(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 104.dp, max = 349.dp)
                        .constrainAs(topSheetSearchCountry) {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        },
                    onCloseTopSheetScreenCallBack = {
                        isShowTopSearchCityScreen = false
                    },
                    onItemClickedCallBack = { cityData ->
                        homeViewModel.requestWeatherData(locationName = cityData.name)
                        isShowTopSearchCityScreen = false
                    }
                )
            }
            when (val viewState = homeScreenViewState.viewState) {
                is ViewState.Success -> {
                    weatherForecastInfoUiModel = viewState.data
                }
                is ViewState.Error -> {
                    LaunchedEffect(key1 = Unit) {
                        scope.launch {
                            scaffoldState.snackbarHostState.showSnackbar(
                                message = viewState.errorMessage.orEmpty(),
                                duration = SnackbarDuration.Long
                            )
                        }
                    }
                }
                is ViewState.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .size(width = 60.dp, height = 60.dp)
                            .constrainAs(progressIndicator) {
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                                top.linkTo(parent.top)
                                bottom.linkTo(parent.bottom)
                            },
                        color = WeatherForecastAppTheme.colors.secondaryBackground
                    )
                }
                is ViewState.Empty -> {
                    LaunchedEffect(key1 = Unit) {
                        scope.launch {
                            scaffoldState.snackbarHostState.showSnackbar(
                                message = emptyDaysInfoMessage,
                                duration = SnackbarDuration.Long
                            )
                        }
                    }
                }
            }
        }
    }
}

private fun getPermissionsArray(): Array<String> =
    arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION
    )


