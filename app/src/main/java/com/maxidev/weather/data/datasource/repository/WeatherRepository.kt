package com.maxidev.weather.data.datasource.repository

import com.maxidev.weather.data.netwotk.model.Weather

interface WeatherRepository {
    suspend fun repoWeather(q: String, days: Int): Weather
}