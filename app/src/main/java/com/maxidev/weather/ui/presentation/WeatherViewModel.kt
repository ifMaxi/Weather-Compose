package com.maxidev.weather.ui.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maxidev.weather.data.repository.WeatherRepositoryImpl
import com.maxidev.weather.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val repositoryImpl: WeatherRepositoryImpl
): ViewModel() {
    private val _uiState: MutableStateFlow<WeatherStatus> =
        MutableStateFlow(WeatherStatus.Loading)
    val uiState: StateFlow<WeatherStatus> = _uiState.asStateFlow()

    private var _search: MutableState<String> = mutableStateOf("")
    var search: State<String> = _search

    fun onSearchChange(query: String) {
        _search.value = query
    }

    init {
        getWeather()
    }

    fun getWeather(query: String? = "") {
        viewModelScope.launch {
            _uiState.value = WeatherStatus.Loading
            delay(2500)
            _uiState.value = try {
                if (query.isNullOrEmpty()) {
                    WeatherStatus.StandBy
                } else {
                    WeatherStatus.Success(
                        onSuccess = repositoryImpl.repoWeather(
                            q = query,
                            days = Constants.THREE_DAYS
                        )
                    )
                }
            } catch (io: IOException) {
                WeatherStatus.Error(onError = io)
            } catch (http: HttpException) {
                WeatherStatus.Error(onError = http)
            }
        }
    }
}