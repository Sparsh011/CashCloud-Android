package com.sparshchadha.stocktracker.feature.stocks.data.remote.dto

data class StockFundamentalsResponse(
    val timeseries: Timeseries
)

data class StockFundamentalsMeta(
    val symbol: List<String>,
    val type: List<String>
)

data class Timeseries(
    val result: List<ResultX>
)

data class TrailingEnterprisesValueEBITDARatio(
    val asOfDate: String,
    val dataId: Int,
    val periodType: String,
    val reportedValue: ReportedValue
)

data class TrailingEnterprisesValueRevenueRatio(
    val asOfDate: String,
    val dataId: Int,
    val periodType: String,
    val reportedValue: ReportedValue
)

data class TrailingEnterpriseValue(
    val asOfDate: String,
    val currencyCode: String,
    val dataId: Int,
    val periodType: String,
    val reportedValue: ReportedValue
)

data class TrailingForwardPeRatio(
    val asOfDate: String,
    val dataId: Int,
    val periodType: String,
    val reportedValue: ReportedValue
)

data class TrailingMarketCap(
    val asOfDate: String,
    val currencyCode: String,
    val dataId: Int,
    val periodType: String,
    val reportedValue: ReportedValue
)

data class TrailingPbRatio(
    val asOfDate: String,
    val dataId: Int,
    val periodType: String,
    val reportedValue: ReportedValue
)

data class TrailingPegRatio(
    val asOfDate: String,
    val dataId: Int,
    val periodType: String,
    val reportedValue: ReportedValue
)

data class TrailingPeRatio(
    val asOfDate: String,
    val dataId: Int,
    val periodType: String,
    val reportedValue: ReportedValue
)

data class TrailingPsRatio(
    val asOfDate: String,
    val dataId: Int,
    val periodType: String,
    val reportedValue: ReportedValue
)

data class ResultX(
    val meta: StockFundamentalsMeta,
    val quarterlyEnterpriseValue: List<QuarterlyEnterpriseValue>,
    val quarterlyEnterprisesValueEBITDARatio: List<QuarterlyEnterprisesValueEBITDARatio>,
    val quarterlyEnterprisesValueRevenueRatio: List<QuarterlyEnterprisesValueRevenueRatio>,
    val quarterlyForwardPeRatio: List<QuarterlyForwardPeRatio>,
    val quarterlyMarketCap: List<QuarterlyMarketCap>,
    val quarterlyPbRatio: List<QuarterlyPbRatio>,
    val quarterlyPeRatio: List<QuarterlyPeRatio>,
    val quarterlyPegRatio: List<QuarterlyPegRatio>,
    val quarterlyPsRatio: List<QuarterlyPsRatio>,
    val timestamp: List<Int>,
    val trailingEnterpriseValue: List<TrailingEnterpriseValue>,
    val trailingEnterprisesValueEBITDARatio: List<TrailingEnterprisesValueEBITDARatio>,
    val trailingEnterprisesValueRevenueRatio: List<TrailingEnterprisesValueRevenueRatio>,
    val trailingForwardPeRatio: List<TrailingForwardPeRatio>,
    val trailingMarketCap: List<TrailingMarketCap>,
    val trailingPbRatio: List<TrailingPbRatio>,
    val trailingPeRatio: List<TrailingPeRatio>,
    val trailingPegRatio: List<TrailingPegRatio>,
    val trailingPsRatio: List<TrailingPsRatio>
)

data class ReportedValue(
    val fmt: String,
    val raw: Double
)

data class QuarterlyPsRatio(
    val asOfDate: String,
    val dataId: Int,
    val periodType: String,
    val reportedValue: ReportedValue
)

data class QuarterlyPeRatio(
    val asOfDate: String,
    val dataId: Int,
    val periodType: String,
    val reportedValue: ReportedValue
)

data class QuarterlyPegRatio(
    val asOfDate: String,
    val dataId: Int,
    val periodType: String,
    val reportedValue: ReportedValue
)

data class QuarterlyPbRatio(
    val asOfDate: String,
    val dataId: Int,
    val periodType: String,
    val reportedValue: ReportedValue
)

data class QuarterlyMarketCap(
    val asOfDate: String,
    val currencyCode: String,
    val dataId: Int,
    val periodType: String,
    val reportedValue: ReportedValue
)

data class QuarterlyForwardPeRatio(
    val asOfDate: String,
    val dataId: Int,
    val periodType: String,
    val reportedValue: ReportedValue
)

data class QuarterlyEnterpriseValue(
    val asOfDate: String,
    val currencyCode: String,
    val dataId: Int,
    val periodType: String,
    val reportedValue: ReportedValue
)

data class QuarterlyEnterprisesValueRevenueRatio(
    val asOfDate: String,
    val currencyCode: String,
    val dataId: Int,
    val periodType: String,
    val reportedValue: ReportedValue
)

data class QuarterlyEnterprisesValueEBITDARatio(
    val asOfDate: String,
    val currencyCode: String,
    val dataId: Int,
    val periodType: String,
    val reportedValue: ReportedValue
)