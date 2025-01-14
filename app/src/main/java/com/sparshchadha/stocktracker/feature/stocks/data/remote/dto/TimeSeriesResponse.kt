package com.sparshchadha.stocktracker.feature.stocks.data.remote.dto

data class StockFundamentalsResponse(
    val timeseries: Timeseries
)

data class Timeseries(
    val result: List<ResultTimeSeries>
)

data class ResultTimeSeries(
    val meta: MetaTimeSeries,
    val timestamp: List<Long>,
    val quarterlyPeRatio: List<QuarterlyPeRatio>? = null,
    val quarterlyForwardPeRatio: List<QuarterlyForwardPeRatio>? = null,
    val trailingEnterprisesValueEBITDARatio: List<TrailingEnterprisesValueEBITDARatio>? = null,
    val quarterlyPegRatio: List<QuarterlyPegRatio>? = null,
    val trailingForwardPeRatio: List<TrailingForwardPeRatio>? = null,
    val trailingMarketCap: List<TrailingMarketCap>? = null,
    val quarterlyPsRatio: List<QuarterlyPsRatio>? = null,
    val quarterlyMarketCap: List<QuarterlyMarketCap>? = null,
    val trailingEnterpriseValue: List<TrailingEnterpriseValue>? = null,
    val quarterlyEnterprisesValueRevenueRatio: List<QuarterlyEnterprisesValueRevenueRatio>? = null,
    val quarterlyEnterprisesValueEBITDARatio: List<QuarterlyEnterprisesValueEBITDARatio>? = null,
    val trailingPegRatio: List<TrailingPegRatio>? = null,
    val trailingPeRatio: List<TrailingPeRatio>? = null,
    val quarterlyEnterpriseValue: List<QuarterlyEnterpriseValue>? = null,
    val trailingEnterprisesValueRevenueRatio: List<TrailingEnterprisesValueRevenueRatio>? = null,
    val trailingPbRatio: List<TrailingPbRatio>? = null,
    val quarterlyPbRatio: List<QuarterlyPbRatio>? = null,
    val trailingPsRatio: List<TrailingPsRatio>? = null
)

data class MetaTimeSeries(
    val symbol: List<String>,
    val type: List<String>
)

data class QuarterlyPeRatio(
    val dataId: Int,
    val asOfDate: String,
    val periodType: String,
    val reportedValue: ReportedValue
)

data class QuarterlyForwardPeRatio(
    val dataId: Int,
    val asOfDate: String,
    val periodType: String,
    val reportedValue: ReportedValue
)

data class TrailingEnterprisesValueEBITDARatio(
    val dataId: Int,
    val asOfDate: String,
    val periodType: String,
    val reportedValue: ReportedValue
)

data class QuarterlyPegRatio(
    val dataId: Int,
    val asOfDate: String,
    val periodType: String,
    val reportedValue: ReportedValue
)

data class TrailingForwardPeRatio(
    val dataId: Int,
    val asOfDate: String,
    val periodType: String,
    val reportedValue: ReportedValue
)

data class TrailingMarketCap(
    val dataId: Int,
    val asOfDate: String,
    val periodType: String,
    val currencyCode: String,
    val reportedValue: ReportedValue
)

data class QuarterlyPsRatio(
    val dataId: Int,
    val asOfDate: String,
    val periodType: String,
    val reportedValue: ReportedValue
)

data class QuarterlyMarketCap(
    val dataId: Int,
    val asOfDate: String,
    val periodType: String,
    val currencyCode: String,
    val reportedValue: ReportedValue
)

data class TrailingEnterpriseValue(
    val dataId: Int,
    val asOfDate: String,
    val periodType: String,
    val currencyCode: String,
    val reportedValue: ReportedValue
)

data class QuarterlyEnterprisesValueRevenueRatio(
    val dataId: Int,
    val asOfDate: String,
    val periodType: String,
    val reportedValue: ReportedValue
)

data class QuarterlyEnterprisesValueEBITDARatio(
    val dataId: Int,
    val asOfDate: String,
    val periodType: String,
    val reportedValue: ReportedValue
)

data class TrailingPegRatio(
    val dataId: Int,
    val asOfDate: String,
    val periodType: String,
    val reportedValue: ReportedValue
)

data class TrailingPeRatio(
    val dataId: Int,
    val asOfDate: String,
    val periodType: String,
    val reportedValue: ReportedValue
)

data class QuarterlyEnterpriseValue(
    val dataId: Int,
    val asOfDate: String,
    val periodType: String,
    val currencyCode: String,
    val reportedValue: ReportedValue
)

data class TrailingEnterprisesValueRevenueRatio(
    val dataId: Int,
    val asOfDate: String,
    val periodType: String,
    val reportedValue: ReportedValue
)

data class TrailingPbRatio(
    val dataId: Int,
    val asOfDate: String,
    val periodType: String,
    val reportedValue: ReportedValue
)

data class QuarterlyPbRatio(
    val dataId: Int,
    val asOfDate: String,
    val periodType: String,
    val reportedValue: ReportedValue
)

data class TrailingPsRatio(
    val dataId: Int,
    val asOfDate: String,
    val periodType: String,
    val reportedValue: ReportedValue
)

data class ReportedValue(
    val raw: Double,
    val fmt: String
)
