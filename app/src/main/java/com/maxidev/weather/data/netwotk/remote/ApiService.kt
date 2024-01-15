package com.maxidev.weather.data.netwotk.remote

import com.maxidev.weather.data.netwotk.model.Weather
import com.maxidev.weather.utils.Constants.WEATHER
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET(WEATHER)
    suspend fun getWeather(
        @Query("q") q: String,
        @Query("days") days: Int
    ): Weather
}