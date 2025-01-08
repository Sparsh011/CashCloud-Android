package com.sparshchadha.stocktracker.feature.stocks.data.remote.dto

data class Regular(
    val end: Int,
    val gmtoffset: Int,
    val start: Int,
    val timezone: String
)