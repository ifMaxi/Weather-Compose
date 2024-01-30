package com.maxidev.weather.ui.presentation.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.maxidev.weather.R
import com.maxidev.weather.ui.theme.soraFamily

@Composable
fun StandByScreen(
    @StringRes text: Int
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(1f))
        LottieComponent(
            animation = R.raw.city_weather,
            modifier = Modifier
                .size(250.dp)
        )
        Text(
            text = stringResource(id = text),
            fontFamily = soraFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 24.sp
        )
        Spacer(modifier = Modifier.weight(1f))
    }
}