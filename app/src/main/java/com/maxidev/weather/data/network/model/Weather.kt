package com.maxidev.weather.data.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Weather(
    @SerialName("location")
    val location: Location,
    @SerialName("current")
    val current: Current,
    @SerialName("forecast")
    val forecast: Forecast
)