package com.sparshchadha.stocktracker.core.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color

@Stable
val primaryAppBackground: Color
    @Composable
    get() = if (isSystemInDarkTheme()) Color(0xFF0B0115) else Color.White

@Stable
val bottomBarBackgroundColor: Color
    @Composable
    get() = if (isSystemInDarkTheme()) Color(0xFF08010F) else secondaryWhite

@Stable
val searchBarBackgroundColor: Color
    @Composable
    get() = if (isSystemInDarkTheme()) Color(0xFF08010F) else secondaryWhite

@Stable
val bottomBarSelectedIconColor: Color
    @Composable
    get() = if (isSystemInDarkTheme()) secondaryWhite else Color(0xFF0A0115)

@Stable
val bottomBarUnselectedIconColor: Color
    @Composable
    get() = if (isSystemInDarkTheme()) Color.DarkGray else Color.LightGray

@Stable
val primaryTextColor: Color
    @Composable
    get() = if (isSystemInDarkTheme()) Color.White else Color.Black

@Stable
val primaryGreen: Color = Color(0xFF00FFA2)

@Stable
val primaryPurple: Color = Color(0xFF8C52FF)

@Stable
val primaryWhite: Color = Color(255, 255, 255, 255)

@Stable
val secondaryWhite: Color = Color(248, 248, 248, 255)

@Stable
val errorRed: Color = Color(0xFFFF0026)