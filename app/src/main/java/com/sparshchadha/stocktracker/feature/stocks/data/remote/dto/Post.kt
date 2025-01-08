package com.sparshchadha.stocktracker.feature.stocks.data.remote.dto

data class Post(
    val end: Int,
    val gmtoffset: Int,
    val start: Int,
    val timezone: String
)