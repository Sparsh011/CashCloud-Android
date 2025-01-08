package com.sparshchadha.stocktracker.feature.stocks.data.remote.dto

data class Result(
    val indicators: Indicators,
    val meta: Meta,
    val timestamp: List<Int>
)