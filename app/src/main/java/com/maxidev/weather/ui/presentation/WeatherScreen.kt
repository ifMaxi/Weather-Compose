package com.maxidev.weather.ui.presentation

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.gestures.ScrollableDefaults
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.MaterialTheme
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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.maxidev.weather.R
import com.maxidev.weather.data.netwotk.model.Weather
import com.maxidev.weather.ui.presentation.components.CardTimeConditions
import com.maxidev.weather.ui.presentation.components.CityName
import com.maxidev.weather.ui.presentation.components.CurrentConditions
import com.maxidev.weather.ui.presentation.components.ErrorScreen
import com.maxidev.weather.ui.presentation.components.LoadingScreen
import com.maxidev.weather.ui.presentation.components.MaxAndFeelsTemperature
import com.maxidev.weather.ui.presentation.components.NextDaysComponent
import com.maxidev.weather.ui.presentation.components.OverviewWeather
import com.maxidev.weather.ui.presentation.components.WeatherCondition
import com.maxidev.weather.ui.presentation.components.WeatherIcons
import com.maxidev.weather.utils.Constants
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherScreen(
    viewModel: WeatherViewModel = viewModel()
) {
    var query = viewModel.search.value
    var active by remember { mutableStateOf(false) }
    val uiState: WeatherStatus by viewModel.uiState.collectAsStateWithLifecycle()
    val scope = rememberCoroutineScope()
    val toast = Toast.makeText(
        LocalContext.current,
        Constants.TOAST_MESSAGE_PROVIDE_LOCATION,
        Toast.LENGTH_SHORT
    )
    val locationList = listOf("Moscow", "Paris", "Buenos Aires", "Las Vegas", "Barcelona")

    Scaffold(
        topBar = {
            Box(Modifier.fillMaxWidth()) {
                SearchBar(
                    modifier = Modifier.align(Alignment.TopCenter),
                    query = query,
                    onQueryChange = { viewModel.onSearchChange(it) },
                    onSearch = {
                        scope.launch {
                            if (query.isEmpty()) {
                                toast.show()
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
                            style = MaterialTheme.typography.bodyMedium
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
                                    style = MaterialTheme.typography.bodyMedium
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
        StatusCheck(status = uiState, modifier = Modifier.padding(paddingValues))
    }
}

@Composable
private fun StatusCheck(
    modifier: Modifier = Modifier,
    status: WeatherStatus
) {
    when (status) {
        is WeatherStatus.Error -> ErrorScreen(errorText = R.string.connection_problems)
        is WeatherStatus.Loading -> LoadingScreen()
        is WeatherStatus.Success -> WeatherInfo(
            weather = status.onSuccess,
            modifier = modifier
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
    val fling: FlingBehavior = ScrollableDefaults.flingBehavior()
    val date = LocalDate.now().dayOfWeek.plus(1)
    val str = date.getDisplayName(TextStyle.FULL, Locale("us"))
    val dateNumber = LocalDate.now().dayOfMonth.plus(1)

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(4.dp)
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        CityName(city = weather.location.name)
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
                humidity = info.humidity.toString()
            )
        }
        Spacer(modifier = Modifier.height(22.dp))
        OverviewWeather(overview = R.string.overview)
        LazyRow(
            modifier = Modifier
                .fillMaxSize()
                .padding(4.dp),
            state = lazyState,
            flingBehavior = fling,
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            val hourList = weather.forecast.forecastday[0].hour

            items(hourList) { hour ->
                val originalFormat = SimpleDateFormat("yyyy-MM-dd HH:mm")
                val formatData = SimpleDateFormat("HH:mm")
                val parseDate: Date? = originalFormat.parse(hour.time)
                val parsedData = parseDate?.let { formatData.format(it) }

                parsedData?.let {
                    CardTimeConditions(
                        icon = hour.condition.icon,
                        temp = hour.tempC.toString(),
                        hour = it
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(22.dp))
        OverviewWeather(overview = R.string.tomorrow)
        weather.forecast.forecastday[1].let { data ->
            NextDaysComponent(
                icon = data.day.condition.icon,
                minTemp = data.day.mintempC.toString(),
                maxTemp = data.day.maxtempC.toString(),
                date = "$str, $dateNumber",
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