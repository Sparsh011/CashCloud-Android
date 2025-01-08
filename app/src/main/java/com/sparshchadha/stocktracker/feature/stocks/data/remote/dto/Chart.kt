package com.sparshchadha.stocktracker.feature.stocks.data.remote.dto

data class Chart(
    val error: Any,
    val result: List<Result>
)