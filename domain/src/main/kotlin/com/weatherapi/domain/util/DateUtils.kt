package com.weatherapi.domain.util

import java.text.SimpleDateFormat
import java.util.*

object DateUtils {
    const val DATE_FORMAT = "EEEE, d MMM yyyy"
    const val TIME_FORMAT = "h:mm a"
    const val DAY_FORMAT = "EEEE"

    fun createISODateFormat(millis: Long, formatType: String): String =
        try {
            val simpleDateFormat = SimpleDateFormat(formatType, Locale.ENGLISH)
            simpleDateFormat.format(millis * 1000)
        } catch (e: Exception) {
            ""
        }
}