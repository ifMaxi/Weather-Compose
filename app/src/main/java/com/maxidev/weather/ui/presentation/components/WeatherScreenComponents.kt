package com.maxidev.weather.ui.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.maxidev.weather.R

/**
 * This file contains all the components that will be seen on the main screen.
 */

// It will show the current location either by default or using the search bar.
// By DEFAULT, a city name will be set.
@Composable
fun CityName(
    modifier: Modifier = Modifier,
    city: String
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(4.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = city,
            style = MaterialTheme.typography.headlineSmall
        )
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
                style = MaterialTheme.typography.headlineSmall
            )
            Text(
                text = "Feels Like: $feelsLike°C",
                style = MaterialTheme.typography.bodySmall
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
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

// A row that will show data for some conditions.
@Composable
fun CurrentConditions(
    modifier: Modifier = Modifier,
    uv: String,
    wind: String,
    humidity: String
) {
    val conditions: List<Triple<String, Int, Int>> = listOf(
        Triple("$wind km", R.drawable.wind, R.string.wind_km),
        Triple(uv, R.drawable.uv, R.string.uv),
        Triple("$humidity %", R.drawable.humidity, R.string.humidity)
    )

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(4.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        conditions.forEach { cond ->
            OutlinedCard(
                modifier = Modifier
                    .padding(4.dp)
                    .size(120.dp),
                elevation = CardDefaults.cardElevation(8.dp)
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
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    style = MaterialTheme.typography.bodySmall
                )
                Text(
                    text = stringResource(id = cond.third),
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    style = MaterialTheme.typography.bodySmall
                )
                Spacer(modifier = Modifier.weight(1f))
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
    precipitationChance: String,
    modifier: Modifier = Modifier
) {
    OutlinedCard(
        modifier = modifier
            .padding(6.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(4.dp),
            verticalArrangement = Arrangement.spacedBy(2.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .padding(4.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier
                        .padding(4.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Text(
                        text = hour,
                        style = MaterialTheme.typography.titleMedium
                    )
                    WeatherIcons(
                        icon = icon,
                        size = DpSize(70.dp, 70.dp)
                    )
                }
            }
            Box(
                modifier = Modifier
                    .padding(4.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier
                        .padding(4.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Text(
                        text = "$temp°C",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        text = "$precipitationChance%",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
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
    rainPercent: String,
    modifier: Modifier = Modifier
) {
    OutlinedCard(
        modifier = modifier
            .padding(10.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
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
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = condition,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
            Box(
                modifier = Modifier
                    .padding(4.dp),
                contentAlignment = Alignment.Center
            ) {
                WeatherIcons(
                    icon = icon,
                    size = DpSize(70.dp, 70.dp)
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
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        text = "$minTemp°",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        text = "$rainPercent %",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}