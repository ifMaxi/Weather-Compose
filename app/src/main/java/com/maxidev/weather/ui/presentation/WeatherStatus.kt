package com.maxidev.weather.ui.presentation

import com.maxidev.weather.data.network.model.Weather

sealed interface WeatherStatus {
    data class Success(val onSuccess: Weather): WeatherStatus
    data class Error(val onError: Exception): WeatherStatus
    data object Loading: WeatherStatus

    data object StandBy: WeatherStatus
}