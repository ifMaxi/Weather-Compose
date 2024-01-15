package com.maxidev.weather.data.datasource

import com.maxidev.weather.data.netwotk.model.Weather
import com.maxidev.weather.data.netwotk.remote.ApiService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import javax.inject.Inject

class WeatherDataSource @Inject constructor(
    private val apiService: ApiService,
    private val ioDispatcher: CoroutineDispatcher = IO
) {
    suspend fun fetchWeather(q: String, days: Int): Weather =
        withContext(ioDispatcher) {
            apiService.getWeather(q, days)
        }
}