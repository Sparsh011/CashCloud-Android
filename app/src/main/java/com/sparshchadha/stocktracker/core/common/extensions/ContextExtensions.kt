package com.sparshchadha.stocktracker.core.common.extensions

import android.content.Context
import android.content.res.Configuration
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.graphics.Color
import androidx.core.content.ContextCompat
import com.sparshchadha.stocktracker.R

fun Context.isDarkThemeOn(): Boolean {
    return resources.configuration.uiMode and
            Configuration.UI_MODE_NIGHT_MASK == UI_MODE_NIGHT_YES
}

fun Context.getPhoneThemeAdjustedBackgroundColor(): Int {
    if (isDarkThemeOn()) return ContextCompat.getColor(this, R.color.background_black)
    return ContextCompat.getColor(this, R.color.white)
}