package com.sparshchadha.stocktracker.core.network

import com.sparshchadha.stocktracker.feature.search.data.remote.dto.SecuritySearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface YahooAPI {

    @GET("/v1/finance/search")
    suspend fun securitySearch(
        @Query("q") query: String
    ): Response<SecuritySearchResponse>

    companion object {
        const val BASE_URL = "https://query1.finance.yahoo.com"
    }
}