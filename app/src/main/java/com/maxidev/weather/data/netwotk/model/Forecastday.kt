package com.maxidev.weather.data.netwotk.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Forecastday(
    @SerialName("date")
    val date: String,
    @SerialName("date_epoch")
    val dateEpoch: Int,
    @SerialName("day")
    val day: Day,
    @SerialName("astro")
    val astro: Astro,
    @SerialName("hour")
    val hour: List<Hour>
)