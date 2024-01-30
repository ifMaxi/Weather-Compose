package com.maxidev.weather.data.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Day(
    @SerialName("maxtemp_c")
    val maxtempC: Double,
    @SerialName("maxtemp_f")
    val maxtempF: Double,
    @SerialName("mintemp_c")
    val mintempC: Double,
    @SerialName("mintemp_f")
    val mintempF: Double,
    @SerialName("avgtemp_c")
    val avgtempC: Double,
    @SerialName("avgtemp_f")
    val avgtempF: Double,
    @SerialName("maxwind_mph")
    val maxwindMph: Double,
    @SerialName("maxwind_kph")
    val maxwindKph: Double,
    @SerialName("totalprecip_mm")
    val totalprecipMm: Double,
    @SerialName("totalprecip_in")
    val totalprecipIn: Double,
    @SerialName("totalsnow_cm")
    val totalsnowCm: Double,
    @SerialName("avgvis_km")
    val avgvisKm: Double,
    @SerialName("avgvis_miles")
    val avgvisMiles: Double,
    @SerialName("avghumidity")
    val avghumidity: Int,
    @SerialName("daily_will_it_rain")
    val dailyWillItRain: Int,
    @SerialName("daily_chance_of_rain")
    val dailyChanceOfRain: Int,
    @SerialName("daily_will_it_snow")
    val dailyWillItSnow: Int,
    @SerialName("daily_chance_of_snow")
    val dailyChanceOfSnow: Int,
    @SerialName("condition")
    val condition: Condition,
    @SerialName("uv")
    val uv: Double
)