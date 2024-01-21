package com.maxidev.weather.data.repository

import com.maxidev.weather.data.datasource.WeatherDataSource
import com.maxidev.weather.data.netwotk.model.Weather
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val dataSource: WeatherDataSource
): WeatherRepository {
    override suspend fun repoWeather(q: String, days: Int): Weather =
        dataSource.fetchWeather(q, days)
}