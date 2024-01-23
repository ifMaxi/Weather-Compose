package com.maxidev.weather.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale

@SuppressLint("SimpleDateFormat")
object DateTimeUtils {
    private val date = LocalDate.now()

    val dayOfWeek: String? = date.dayOfWeek
        .plus(1)
        .getDisplayName(
            TextStyle.FULL,
            Locale("us")
        )

    val dayOfMonth = date.dayOfMonth
        .plus(1)

    val formatOriginal = SimpleDateFormat("yyyy-MM-dd HH:mm")
    val formatHour = SimpleDateFormat("HH")
}