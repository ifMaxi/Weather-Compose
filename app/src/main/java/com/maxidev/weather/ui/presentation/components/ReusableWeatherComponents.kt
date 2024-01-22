package com.maxidev.weather.ui.presentation.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest

/**
 * It contains a series of composables that will be reused in the app.
 * From images, title bars, and much more.
 */

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

// A dividing section.
@Composable
fun SectionsWeather(
    modifier: Modifier = Modifier,
    @StringRes overview: Int
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Text(
            text = stringResource(id = overview)
        )
    }
}