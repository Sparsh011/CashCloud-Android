package com.sparshchadha.stocktracker.feature.stocks.domain.repository

import com.sparshchadha.stocktracker.core.common.utils.UiState
import com.sparshchadha.stocktracker.feature.stocks.data.remote.dto.StockChartResponse
import com.sparshchadha.stocktracker.feature.stocks.data.remote.dto.StockFundamentalsResponse

interface StockRepository {
    suspend fun getStockChartDetails(
        identifier: String,
        startFromEpoch: Long,
        endEpoch: Long
    ): UiState<StockChartResponse>

    suspend fun getStockFundamentals(
        identifier: String,
        startFromEpoch: Long,
        endEpoch: Long
    ): UiState<StockFundamentalsResponse>
}