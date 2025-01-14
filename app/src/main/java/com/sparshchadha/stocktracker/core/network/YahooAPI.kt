package com.sparshchadha.stocktracker.core.network

import com.sparshchadha.stocktracker.feature.search.data.remote.dto.SecuritySearchResponse
import com.sparshchadha.stocktracker.feature.stocks.data.remote.dto.StockChartResponse
import com.sparshchadha.stocktracker.feature.stocks.data.remote.dto.StockFundamentalsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface YahooAPI {

    @GET("/v1/finance/search")
    suspend fun securitySearch(
        @Query("q") query: String
    ): Response<SecuritySearchResponse>

    @GET("v8/finance/chart/{symbol}")
    suspend fun getStockChart(
        @Path("symbol") symbol: String,
        @Query("period1") startFromEpoch: Long,
        @Query("period2") endEpoch: Long,
        @Query("lang") language: String = "en-US",
        @Query("interval") interval: String,
        @Query("includePrePost") includePrePost: Boolean = true
    ): Response<StockChartResponse>


    @GET("ws/fundamentals-timeseries/v1/finance/timeseries/{symbol}")
    suspend fun getStockFundamentals(
        @Path("symbol") symbol: String,
        @Query("period1") startFromEpoch: Long,
        @Query("period2") endEpoch: Long,
        @Query("lang") language: String = "en-US",
        @Query("type") type: String = """
            quarterlyMarketCap,trailingMarketCap,quarterlyEnterpriseValue,trailingEnterpriseValue,
            quarterlyPeRatio,trailingPeRatio,quarterlyForwardPeRatio,trailingForwardPeRatio,
            quarterlyPegRatio,trailingPegRatio,quarterlyPsRatio,trailingPsRatio,
            quarterlyPbRatio,trailingPbRatio,quarterlyEnterprisesValueRevenueRatio,
            trailingEnterprisesValueRevenueRatio,quarterlyEnterprisesValueEBITDARatio,
            trailingEnterprisesValueEBITDARatio
        """.trimIndent()
    ): Response<StockFundamentalsResponse>

    companion object {
        const val BASE_URL = "https://query1.finance.yahoo.com"
    }
}