package com.maxidev.weather.data.network.remote

import com.maxidev.weather.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor: Interceptor {
    private val apiKey = BuildConfig.KEY

    override fun intercept(chain: Interceptor.Chain): Response {
        var original = chain.request()
        val url = original.url
            .newBuilder()
            .addQueryParameter("key", apiKey)
            .build()

        original = original
            .newBuilder()
            .url(url)
            .build()

        return chain.proceed(original)
    }
}