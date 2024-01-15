package com.maxidev.weather.ui.presentation.previews

import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Preview

@Preview(
    name = "Light",
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Preview(
    name = "Night",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
annotation class ThemePreview