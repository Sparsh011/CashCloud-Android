package com.sparshchadha.stocktracker.feature.stocks.data.remote.dto

data class TradingPeriods(
    val post: List<List<Post>>,
    val pre: List<List<Pre>>,
    val regular: List<List<Regular>>
)