package com.maxidev.weather.ui.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest

// An image that describes the current weather condition.
@Composable
fun WeatherIcons(
    icon: String,
    size: DpSize,
    modifier: Modifier = Modifier
) {
    val replaceHttps = "https:$icon"
    val localContext = LocalContext.current
    val imageRequest = ImageRequest.Builder(localContext)
        .data(replaceHttps)
        .crossfade(true)
        .memoryCachePolicy(CachePolicy.ENABLED)
        .build()

    Box(
        modifier = modifier
            .padding(4.dp),
        contentAlignment = Alignment.Center
    ) {
        AsyncImage(
            model = imageRequest,
            contentDescription = null,
            modifier = Modifier
                .size(size)
        )
    }
}