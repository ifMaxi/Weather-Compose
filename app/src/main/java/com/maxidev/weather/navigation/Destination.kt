package com.maxidev.weather.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CalendarToday
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.WbSunny
import androidx.compose.ui.graphics.vector.ImageVector
import com.maxidev.weather.R

sealed class Destination(
    val route: String,
    @StringRes val resourceId: Int,
    val icon: ImageVector
) {
    data object CurrentForecastScreen: Destination(
        route = "current_forecast_screen",
        resourceId = R.string.current,
        icon = Icons.Outlined.WbSunny
    )
    data object DaysForecastScreen: Destination(
        route = "days_forecast_screen",
        resourceId = R.string.day_forecast,
        icon = Icons.Outlined.CalendarToday
    )
    data object SearchWeatherScreen: Destination(
        route = "search_weather_screen",
        resourceId = R.string.weather_search_screen,
        icon = Icons.Outlined.Search
    )
}