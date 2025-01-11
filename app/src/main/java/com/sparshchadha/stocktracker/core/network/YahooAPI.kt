package com.sparshchadha.stocktracker.core.network

import com.sparshchadha.stocktracker.feature.search.data.remote.dto.SecuritySearchResponse
import com.sparshchadha.stocktracker.feature.stocks.data.remote.dto.StockChartResponse
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
//        @Query("period1") startFromEpoch: Long,
//        @Query("period2") endEpoch: Long,
        @Query("lang") language: String = "en-US",
        @Query("includePrePost") includePrePost: Boolean = true
    ): Response<StockChartResponse>

    companion object {
        const val BASE_URL = "https://query1.finance.yahoo.com"
    }
}