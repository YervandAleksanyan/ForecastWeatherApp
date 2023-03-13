package com.weatherapi.home_screen.viewmodels

import android.location.Location
import androidx.compose.runtime.Stable
import androidx.lifecycle.viewModelScope
import com.weatherapi.core.locationprovider.LocationListener
import com.weatherapi.core.locationprovider.LocationProviderClient
import com.weatherapi.core.view_models.BaseViewModel
import com.weatherapi.core.view_state.ViewState
import com.weatherapi.domain.models.SendInfoRequestModel
import com.weatherapi.domain.models.WeatherDomainModel
import com.weatherapi.domain.usecases.GetForecastInfoDataUseCase
import com.weatherapi.domain.util.RequestResult
import com.weatherapi.domain.util.error
import com.weatherapi.home_screen.mappers.WeatherDomainToUiModelMapper
import com.weatherapi.home_screen.models.HomeScreenSideEffect
import com.weatherapi.home_screen.models.HomeScreenViewState
import com.weatherapi.home_screen.models.WeatherForecastInfoUiModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

@Stable
internal class HomeScreenViewModel(
    private val getForecastInfoDataUseCase: GetForecastInfoDataUseCase,
    private val locationProviderClient: LocationProviderClient,
    private val uiModelMapper: WeatherDomainToUiModelMapper,
) : ContainerHost<HomeScreenViewState, HomeScreenSideEffect>, BaseViewModel() {

    companion object {
        const val DEFAULT_LOCATION_NAME = "San Francisco"
    }

    override val container = container<HomeScreenViewState, HomeScreenSideEffect>(
        HomeScreenViewState.initial()
    )

    private val viewStateMutableStateFlow =
        MutableStateFlow<ViewState<WeatherForecastInfoUiModel>>(ViewState.Loading)

    fun requestWeatherData(locationName: String = DEFAULT_LOCATION_NAME) {
        viewModelScope.launch {
            postViewState(ViewState.Loading)
            val response =
                getForecastInfoDataUseCase.execute(param = SendInfoRequestModel(locationName = locationName))
            postViewState(toViewState(responseData = response))
        }
    }

    fun requestCurrentLocation() {
        viewModelScope.launch {
            locationProviderClient.getCurrentLocation()
            locationProviderClient.listener = object : LocationListener {
                override fun onLocationResult(locationList: Location) {
                    requestWeatherData(locationName = "${locationList.latitude},${locationList.longitude}")
                }
            }
        }
    }

    private suspend fun postViewState(newViewState: ViewState<WeatherForecastInfoUiModel>) {
        viewStateMutableStateFlow.emit(newViewState)
        reducer()
    }

    private suspend fun toViewState(responseData: RequestResult<WeatherDomainModel>) =
        when (responseData) {
            is RequestResult.Success -> {
                if (responseData.data.forecastInfo.forecastDayListInfo.isEmpty()) {
                    postViewState(ViewState.Empty)
                }
                ViewState.Success(data = uiModelMapper.mapFrom(input = responseData.data))
            }
            is RequestResult.Error -> {
                val error = responseData.error?.cause
                val errorMessage = responseData.error?.message.orEmpty()
                ViewState.Error(
                    errorData = null,
                    error = error,
                    errorMessage = errorMessage
                )
            }
        }

    private fun reducer() {
        intent {
            reduce {
                HomeScreenViewState(viewStateMutableStateFlow.value)
            }
        }
    }
}