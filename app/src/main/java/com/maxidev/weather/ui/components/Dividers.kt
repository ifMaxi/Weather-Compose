package com.maxidev.weather.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Dividers(modifier: Modifier = Modifier) {
    Divider(
        modifier = modifier
            .padding(
                start = 10.dp
            )
            .width(180.dp),
        color = MaterialTheme.colorScheme.outline,
        thickness = 2.dp
    )
}