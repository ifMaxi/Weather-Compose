package com.maxidev.weather.ui.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.maxidev.weather.R
import com.maxidev.weather.ui.theme.soraFamily

/**
 * This file contains all the components that will be seen on the main screen.
 */

// It will show the current location either by default or using the search bar.
// By DEFAULT, a city name will be set.
@Composable
fun CityName(
    modifier: Modifier = Modifier,
    city: String,
    country: String
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(4.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = city,
                fontFamily = soraFamily,
                fontSize = 34.sp
            )
            Text(
                text = country,
                fontFamily = soraFamily,
                fontSize = 20.sp
            )
        }
    }
}

// It will show the maximum and feels like temperatures of the current day.
@Composable
fun MaxAndFeelsTemperature(
    modifier: Modifier = Modifier,
    maxTemp: String,
    feelsLike: String
) {
    Box(
        modifier = modifier
            .padding(4.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .padding(4.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "$maxTemp°C",
                fontFamily = soraFamily,
                fontSize = 26.sp
            )
            Text(
                text = "Feels Like: $feelsLike°C",
                fontFamily = soraFamily,
                fontSize = 14.sp

            )
        }
    }
}

// A string that will describe the current weather condition.
@Composable
fun WeatherCondition(
    modifier: Modifier = Modifier,
    condition: String
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(4.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = condition,
            fontFamily = soraFamily,
            fontSize = 20.sp
        )
    }
}

// A row that will show data for some conditions.
@Composable
fun CurrentConditions(
    uv: String,
    wind: String,
    humidity: String,
    cloudCover: String,
    pressure: String,
    precipitation: String,
) {
    val lazyState = rememberLazyGridState()
    val conditions: List<Triple<String, Int, Int>> = listOf(
        Triple("$wind km", R.drawable.wind, R.string.wind_km),
        Triple(uv, R.drawable.uv, R.string.uv),
        Triple("$humidity %", R.drawable.humidity, R.string.humidity),
        Triple("$cloudCover%", R.drawable.overcast, R.string.clouds),
        Triple("$pressure mBar", R.drawable.pressure, R.string.pressure),
        Triple("$precipitation mm", R.drawable.precipitation, R.string.precipitation)
    )

    LazyHorizontalGrid(
        modifier = //shadowWithShapeModifier
        Modifier
            .fillMaxWidth()
            .heightIn(min = 0.dp, max = 240.dp),
            //.background(MaterialTheme.colorScheme.surfaceVariant),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalArrangement = Arrangement.Center,
        rows = GridCells.Fixed(2),
        state = lazyState
    ) {
        items(conditions) { cond ->
            Box(
                modifier = Modifier
                    .padding(4.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier
                        .padding(4.dp)
                        .size(100.dp),
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.weight(1f))
                    Image(
                        painter = painterResource(id = cond.second),
                        contentDescription = null,
                        modifier = Modifier
                            .size(40.dp)
                            .align(Alignment.CenterHorizontally)
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = cond.first,
                        fontFamily = soraFamily,
                        fontSize = 14.sp,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                    Text(
                        text = stringResource(id = cond.third),
                        fontFamily = soraFamily,
                        fontSize = 14.sp,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}

// Component to be displayed using a "LazyLayout".
// Its main function is to display some data for each hour of the day.
@Composable
fun CardTimeConditions(
    icon: String,
    temp: String,
    hour: String,
    precipitationChance: String
) {
    Column(
        modifier = Modifier
            .size(height = 140.dp, width = 90.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = hour,
            fontFamily = soraFamily,
            fontSize = 14.sp
        )
        Spacer(modifier = Modifier.weight(1f))
        WeatherIcons(
            icon = icon,
            size = DpSize(50.dp, 50.dp)
        )
        Text(
            text = "$temp°",
            fontFamily = soraFamily,
            fontSize = 14.sp
        )
        Text(
            text = "$precipitationChance%",
            fontFamily = soraFamily,
            fontSize = 14.sp
        )
    }
}

// It will show some data for the next day.
@Composable
fun NextDaysComponent(
    icon: String,
    minTemp: String,
    maxTemp: String,
    date: String,
    condition: String,
    rainPercent: String
) {
    Row(
        modifier =
        Modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .padding(4.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .padding(4.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Text(
                    text = date,
                    fontFamily = soraFamily,
                    fontSize = 24.sp
                )
                Text(
                    text = condition,
                    fontFamily = soraFamily,
                    fontSize = 14.sp
                )
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        Box(
            modifier = Modifier
                .padding(4.dp),
            contentAlignment = Alignment.Center
        ) {
            WeatherIcons(
                icon = icon,
                size = DpSize(70.dp, 70.dp),
                modifier = Modifier
            )
        }
        Box(
            modifier = Modifier
                .padding(4.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .padding(4.dp),
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = "$maxTemp°",
                    fontFamily = soraFamily,
                    fontSize = 14.sp
                )
                Text(
                    text = "$minTemp°",
                    fontFamily = soraFamily,
                    fontSize = 14.sp
                )
                Text(
                    text = "$rainPercent %",
                    fontFamily = soraFamily,
                    fontSize = 14.sp
                )
            }
        }
    }
}

@Composable
fun AstroWeather(
    sunrise: String,
    sunset: String,
    moonrise: String,
    moonset: String
) {
    val sunMoon = listOf(
        Triple("Sunrise", R.drawable.morning, sunrise),
        Triple("Sunset", R.drawable.afternoon, sunset),
        Triple("Moonrise", R.drawable.evening, moonrise),
        Triple("Moonset", R.drawable.nigth, moonset)
    )

    Column(
        modifier = //shadowWithShapeModifier
        Modifier
            .fillMaxWidth()
            .height(120.dp),
            //.background(MaterialTheme.colorScheme.surfaceVariant),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            sunMoon.forEach { cond ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Text(
                        text = cond.first,
                        fontFamily = soraFamily,
                        fontSize = 16.sp
                    )
                    Image(
                        painter = painterResource(id = cond.second),
                        contentDescription = null,
                        modifier = Modifier
                            .size(40.dp)
                    )
                    Text(
                        text = cond.third,
                        fontFamily = soraFamily,
                        fontSize = 14.sp
                    )
                }
            }
        }
    }
}