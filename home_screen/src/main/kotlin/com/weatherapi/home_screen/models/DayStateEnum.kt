package com.weatherapi.home_screen.models

import androidx.annotation.StringRes
import com.weatherapi.core.R

enum class DayStateEnum(@StringRes val dayNameStringResId: Int) {
    TODAY(dayNameStringResId = R.string.day_today_name),
    TOMORROW(dayNameStringResId = R.string.day_tomorrow_name)
}