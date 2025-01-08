package com.sparshchadha.stocktracker.feature.stocks.data.remote.dto

data class Quote(
    val close: List<Double>,
    val high: List<Double>,
    val low: List<Double>,
    val `open`: List<Double>,
    val volume: List<Int>
)