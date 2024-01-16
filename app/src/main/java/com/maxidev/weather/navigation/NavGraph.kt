package com.maxidev.weather.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.maxidev.weather.ui.presentation.WeatherScreen
import com.maxidev.weather.ui.presentation.WeatherStartScreen

@Composable
fun NavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = Destination.START_SCREEN.route
) {
    val secondDestination = Destination.WEATHER_SCREEN.route

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(route = startDestination) {
            WeatherStartScreen(
                onClick = {
                    navController.navigate(secondDestination)
                }
            )
        }
        composable(route = secondDestination) {
            WeatherScreen()
        }
    }
}