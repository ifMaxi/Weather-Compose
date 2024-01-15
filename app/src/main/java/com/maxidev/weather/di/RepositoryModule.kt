package com.maxidev.weather.di

import com.maxidev.weather.data.datasource.WeatherDataSource
import com.maxidev.weather.data.datasource.repository.WeatherRepository
import com.maxidev.weather.data.datasource.repository.WeatherRepositoryImpl
import com.maxidev.weather.data.netwotk.remote.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @[Provides Singleton]
    fun providesWeatherDatasource(apiService: ApiService): WeatherDataSource =
        WeatherDataSource(apiService)

    @[Provides Singleton]
    fun providesWeatherRepository(dataSource: WeatherDataSource): WeatherRepository =
        WeatherRepositoryImpl(dataSource)
}