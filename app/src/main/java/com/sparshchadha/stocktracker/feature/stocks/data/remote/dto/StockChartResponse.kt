package com.sparshchadha.stocktracker.feature.stocks.data.remote.dto

data class StockChartResponse(
    val chart: Chart
)

data class TradingPeriods(
    val post: List<List<Post>>,
    val pre: List<List<Pre>>,
    val regular: List<List<Regular>>
)

data class Regular(
    val end: Int,
    val gmtoffset: Int,
    val start: Int,
    val timezone: String
)

data class Result(
    val indicators: Indicators,
    val meta: Meta,
    val timestamp: List<Int>
)

data class Quote(
    val close: List<Double>,
    val high: List<Double>,
    val low: List<Double>,
    val `open`: List<Double>,
    val volume: List<Int>
)

data class Pre(
    val end: Int,
    val gmtoffset: Int,
    val start: Int,
    val timezone: String
)

data class Post(
    val end: Int,
    val gmtoffset: Int,
    val start: Int,
    val timezone: String
)

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
    val regularMarketDayHigh: Double?,
    val regularMarketDayLow: Double?,
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

data class Indicators(
    val quote: List<Quote>
)

data class CurrentTradingPeriod(
    val post: Post,
    val pre: Pre,
    val regular: Regular
)

data class Chart(
    val error: Any,
    val result: List<Result>
)