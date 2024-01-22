package com.maxidev.weather.ui.presentation.previews

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import com.maxidev.weather.R
import com.maxidev.weather.ui.presentation.WeatherIconAndTemps
import com.maxidev.weather.ui.presentation.WeatherStartScreen
import com.maxidev.weather.ui.presentation.components.CardTimeConditions
import com.maxidev.weather.ui.presentation.components.CityName
import com.maxidev.weather.ui.presentation.components.CurrentConditions
import com.maxidev.weather.ui.presentation.components.NextDaysComponent
import com.maxidev.weather.ui.presentation.components.SectionsWeather
import com.maxidev.weather.ui.theme.WeatherTheme

@ThemePreview
@Composable
private fun CityPreview() {
    WeatherTheme {
        Surface {
            CityName(city = "Venado Tuerto")
        }
    }
}

@ThemePreview
@Composable
private fun IconsTempPreview() {
    WeatherTheme {
        Surface {
            WeatherIconAndTemps(
                icon = "Icon",
                maxTemp = "25.4",
                feelsLike = "30.4",
                condition = "Sunny"
            )
        }
    }
}

@ThemePreview
@Composable
private fun OverviewPreview() {
    WeatherTheme {
        Surface {
            SectionsWeather(overview = R.string.overview)
        }
    }
}

@ThemePreview
@Composable
private fun CurrentConditionsPreview() {
    WeatherTheme {
        Surface {
            CurrentConditions(
                uv = "7",
                wind = "12.5",
                humidity = "48",
                cloudCover = "",
                pressure = "",
                precipitation = ""
            )
        }
    }
}

@ThemePreview
@Composable
private fun CardTimePreview() {
    WeatherTheme {
        Surface {
            CardTimeConditions(
                icon = "Icon",
                temp = "28.7",
                hour = "17",
                precipitationChance = "70"
            )
        }
    }
}

@ThemePreview
@Composable
private fun NextDayPreview() {
    WeatherTheme {
        Surface {
            NextDaysComponent(
                icon = "Icon",
                minTemp = "10.3",
                maxTemp = "29.9",
                date = "Monday, 29",
                condition = "Sunny",
                rainPercent = "2"
            )
        }
    }
}

@ThemePreview
@Composable
private fun AnimationLottiePreview() {
    WeatherTheme {
        Surface {
            WeatherStartScreen(onClick = {})
        }
    }
}