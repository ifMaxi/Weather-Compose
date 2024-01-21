package com.maxidev.weather.data.repository

import com.maxidev.weather.data.netwotk.model.Weather

interface WeatherRepository {
    suspend fun repoWeather(q: String, days: Int): Weather
}