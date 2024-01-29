package com.maxidev.weather

interface FakeRepository {
    suspend fun repWeather(q: String): FakeModelWeather
}

class FakeRepositoryImpl: FakeRepository {
    override suspend fun repWeather(q: String): FakeModelWeather {
        return FakeModelWeather(location = q)
    }
}