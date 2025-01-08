package com.sparshchadha.stocktracker.feature.stocks.data.remote.dto

data class CurrentTradingPeriod(
    val post: Post,
    val pre: Pre,
    val regular: Regular
)