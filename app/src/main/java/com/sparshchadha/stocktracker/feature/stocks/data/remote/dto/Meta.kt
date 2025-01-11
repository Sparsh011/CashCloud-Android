package com.sparshchadha.stocktracker.feature.stocks.data.remote.dto

data class Meta(
    val chartPreviousClose: Double,
    val currency: String,
    val currentTradingPeriod: CurrentTradingPeriod,
    val dataGranularity: String,
    val exchangeName: String,
    val exchangeTimezoneName: String,
    val fiftyTwoWeekHigh: Double,
    val fiftyTwoWeekLow: Double,
    val firstTradeDate: Int,
    val fullExchangeName: String,
    val gmtoffset: Int,
    val hasPrePostMarketData: Boolean,
    val instrumentType: String,
    val longName: String,
    val previousClose: Double,
    val priceHint: Int,
    val range: String,
    val regularMarketDayHigh: Double,
    val regularMarketDayLow: Double,
    val regularMarketPrice: Double,
    val regularMarketTime: Int,
    val regularMarketVolume: Int,
    val scale: Int,
    val shortName: String,
    val symbol: String,
    val timezone: String,
    val tradingPeriods: TradingPeriods,
    val validRanges: List<String>
)