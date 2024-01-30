package com.maxidev.weather.ui.presentation

import android.annotation.SuppressLint
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.LocationCity
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.maxidev.weather.R
import com.maxidev.weather.data.network.model.Weather
import com.maxidev.weather.ui.components.Dividers
import com.maxidev.weather.ui.presentation.components.AstroWeather
import com.maxidev.weather.ui.presentation.components.CardTimeConditions
import com.maxidev.weather.ui.presentation.components.CityName
import com.maxidev.weather.ui.presentation.components.CurrentConditions
import com.maxidev.weather.ui.presentation.components.ErrorScreen
import com.maxidev.weather.ui.presentation.components.LoadingScreen
import com.maxidev.weather.ui.presentation.components.MaxAndFeelsTemperature
import com.maxidev.weather.ui.presentation.components.NextDaysComponent
import com.maxidev.weather.ui.presentation.components.SectionsWeather
import com.maxidev.weather.ui.presentation.components.StandByScreen
import com.maxidev.weather.ui.presentation.components.WeatherCondition
import com.maxidev.weather.ui.presentation.components.WeatherIcons
import com.maxidev.weather.ui.theme.soraFamily
import com.maxidev.weather.utils.ClimateUtilities.locationList
import com.maxidev.weather.utils.Constants.TOAST_MESSAGE_PROVIDE_LOCATION
import com.maxidev.weather.utils.DateTimeUtils
import kotlinx.coroutines.launch
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherScreen(
    viewModel: WeatherViewModel = hiltViewModel()
) {
    var query = viewModel.search.value
    var active by remember { mutableStateOf(false) }
    val uiState: WeatherStatus by viewModel.uiState.collectAsStateWithLifecycle()
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val toast = Toast.makeText(context, TOAST_MESSAGE_PROVIDE_LOCATION, LENGTH_SHORT)

    Scaffold(
        topBar = {
            Box(Modifier.fillMaxWidth()) {
                SearchBar(
                    modifier = Modifier.align(Alignment.TopCenter),
                    query = query,
                    onQueryChange = viewModel::onSearchChange,
                    onSearch = {
                        scope.launch {
                            if (query.isEmpty()) {
                                toast.show()
                                active = false
                            } else {
                                active = false
                                viewModel.getWeather(it)
                            }
                        }
                    },
                    active = active,
                    onActiveChange = { active = true },
                    placeholder = {
                        Text(
                            text = stringResource(id = R.string.enter_location),
                            fontFamily = soraFamily,
                            fontSize = 14.sp
                        )
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.Search,
                            contentDescription = null
                        )
                    },
                    tonalElevation = SearchBarDefaults.Elevation
                ) {
                    locationList.forEach { location ->
                        ListItem(
                            modifier = Modifier
                                .clickable {
                                    query = location
                                    viewModel.getWeather(location)
                                    active = false
                                },
                            headlineContent = {
                                Text(
                                    text = location,
                                    fontFamily = soraFamily,
                                    fontSize = 14.sp
                                )
                            },
                            leadingContent = {
                                Icon(
                                    imageVector = Icons.Outlined.LocationCity,
                                    contentDescription = null
                                )
                            },
                            tonalElevation = ListItemDefaults.Elevation,
                            shadowElevation = ListItemDefaults.Elevation
                        )
                        Divider()
                    }
                }
            }
        }
    ) { paddingValues ->
        StatusCheck(
            status = uiState,
            onClick = { viewModel.getWeather(query) },
            modifier = Modifier.padding(paddingValues)
        )
    }
}

@Composable
private fun StatusCheck(
    modifier: Modifier = Modifier,
    status: WeatherStatus,
    onClick: () -> Unit
) {
    when (status) {
        is WeatherStatus.Error -> ErrorScreen(
            errorText = R.string.connection_lost,
            onClick = onClick
        )
        is WeatherStatus.Loading -> LoadingScreen()
        is WeatherStatus.Success -> WeatherInfo(
            weather = status.onSuccess,
            modifier = modifier
        )
        is WeatherStatus.StandBy -> StandByScreen(
            text = R.string.stand_by
        )
    }
}

@SuppressLint("SimpleDateFormat")
@Composable
fun WeatherInfo(
    modifier: Modifier = Modifier,
    weather: Weather
) {
    val scrollState: ScrollState = rememberScrollState()
    val lazyState = rememberLazyListState()
    val hourList = weather.forecast.forecastday[0].hour
    val lazyHour by remember { mutableStateOf(hourList) }
    val heightSpace = Modifier.height(40.dp)

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(4.dp)
            .verticalScroll(scrollState)
    ) {
        Spacer(modifier = Modifier.weight(1f))
        CityName(
            city = weather.location.name,
            country = weather.location.country
        )
        weather.current.let { info ->
            WeatherIconAndTemps(
                icon = info.condition.icon,
                maxTemp = info.tempC.toString(),
                feelsLike = info.feelslikeC.toString(),
                condition = info.condition.text
            )
            CurrentConditions(
                uv = info.uv.toString(),
                wind = info.windKph.toString(),
                humidity = info.humidity.toString(),
                cloudCover = info.cloud.toString(),
                pressure = info.pressureMb.toString(),
                precipitation = info.precipMm.toString(),

                )
        }
        Spacer(modifier = heightSpace)
        SectionsWeather(
            overview = R.string.overview
        )
        Dividers()
        LazyRow(
            modifier = Modifier
                .fillMaxWidth(),
            state = lazyState,
            contentPadding = PaddingValues(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items(lazyHour) { hour ->
                val parseDate: Date? = DateTimeUtils.formatOriginal.parse(hour.time)
                val parsedData = parseDate?.let { DateTimeUtils.formatHour.format(it) }

                parsedData?.let {
                    CardTimeConditions(
                        icon = hour.condition.icon,
                        temp = hour.tempC.toString(),
                        precipitationChance = hour.chanceOfRain.toString(),
                        hour = "$it Hr"
                    )
                }
            }
        }
        Spacer(modifier = heightSpace)
        SectionsWeather(
            overview = R.string.astro
        )
        Dividers()
        weather.forecast.forecastday[1].let { astro ->
            AstroWeather(
                sunrise = astro.astro.sunrise,
                sunset = astro.astro.sunset,
                moonrise = astro.astro.moonrise,
                moonset = astro.astro.moonset
            )
        }
        Spacer(heightSpace)
        SectionsWeather(
            overview = R.string.tomorrow
        )
        Dividers()
        weather.forecast.forecastday[1].let { data ->
            NextDaysComponent(
                icon = data.day.condition.icon,
                minTemp = data.day.mintempC.toString(),
                maxTemp = data.day.maxtempC.toString(),
                date = "${DateTimeUtils.dayOfWeek}, ${DateTimeUtils.dayOfMonth}",
                condition = data.day.condition.text,
                rainPercent = data.day.dailyChanceOfRain.toString()
            )
        }
    }
}

@Composable
fun WeatherIconAndTemps(
    modifier: Modifier = Modifier,
    icon: String,
    maxTemp: String,
    feelsLike: String,
    condition: String
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        WeatherIcons(
            icon = icon,
            size = DpSize(100.dp, 100.dp)
        )
        MaxAndFeelsTemperature(
            maxTemp = maxTemp,
            feelsLike = feelsLike
        )
        WeatherCondition(
            condition = condition
        )
    }
}