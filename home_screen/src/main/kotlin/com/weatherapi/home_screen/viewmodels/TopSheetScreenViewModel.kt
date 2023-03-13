package com.weatherapi.home_screen.viewmodels

import androidx.compose.runtime.Stable
import androidx.lifecycle.viewModelScope
import com.weatherapi.core.view_models.BaseViewModel
import com.weatherapi.core.view_state.ViewState
import com.weatherapi.domain.models.SearchCityDomainModel
import com.weatherapi.domain.usecases.SearchCityUseCase
import com.weatherapi.domain.util.RequestResult
import com.weatherapi.domain.util.error
import com.weatherapi.home_screen.mappers.SearchCityDomainToUIMapper
import com.weatherapi.home_screen.models.searchcity.SearchCityItemUIModel
import com.weatherapi.home_screen.models.searchcity.TopSheetScreenSideEffect
import com.weatherapi.home_screen.models.searchcity.TopSheetScreenViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

@Stable
internal class TopSheetScreenViewModel(
    private val searchCityUseCase: SearchCityUseCase,
    private val uiModelMapper: SearchCityDomainToUIMapper
) : ContainerHost<TopSheetScreenViewState, TopSheetScreenSideEffect>, BaseViewModel() {

    override val container = container<TopSheetScreenViewState, TopSheetScreenSideEffect>(
        TopSheetScreenViewState.initial()
    )

    private val viewStateMutableStateFlow =
        MutableStateFlow<ViewState<List<SearchCityItemUIModel>>>(ViewState.Loading)

    fun searchCity(city: String) {
        viewModelScope.launch {
            val response =
                searchCityUseCase.execute(param = city)
            postViewState(toViewState(responseData = response))
        }
    }

    fun cityItemClicked(data: SearchCityItemUIModel) {
        intent {
            postSideEffect(
                TopSheetScreenSideEffect.CloseSearchView(selectData = data, itemClicked = true)
            )
        }
    }

    fun closeScreen() {
        intent {
            postSideEffect(
                TopSheetScreenSideEffect.CloseSearchView(selectData = null, itemClicked = false)
            )
        }
    }

    private suspend fun postViewState(newViewState: ViewState<List<SearchCityItemUIModel>>) {
        viewStateMutableStateFlow.emit(newViewState)
        reducer()
    }

    private fun toViewState(responseData: RequestResult<List<SearchCityDomainModel>>) =
        when (responseData) {
            is RequestResult.Success -> {
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
                TopSheetScreenViewState(viewStateMutableStateFlow.value)
            }
        }
    }
}