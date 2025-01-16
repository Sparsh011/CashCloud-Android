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
    val end: Long,
    val gmtoffset: Long,
    val start: Long,
    val timezone: String
)

data class Result(
    val indicators: Indicators,
    val meta: Meta,
    val timestamp: List<Long>
)

data class Quote(
    val close: List<Double>,
    val high: List<Double>,
    val low: List<Double>,
    val `open`: List<Double>,
    val volume: List<Long>
)

data class Pre(
    val end: Long,
    val gmtoffset: Long,
    val start: Long,
    val timezone: String
)

data class Post(
    val end: Long,
    val gmtoffset: Long,
    val start: Long,
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
    val firstTradeDate: Long,
    val fullExchangeName: String,
    val gmtoffset: Long,
    val hasPrePostMarketData: Boolean,
    val instrumentType: String,
    val longName: String,
    val previousClose: Double,
    val priceHint: Int,
    val range: String,
    val regularMarketDayHigh: Double?,
    val regularMarketDayLow: Double?,
    val regularMarketPrice: Double,
    val regularMarketTime: Long,
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
