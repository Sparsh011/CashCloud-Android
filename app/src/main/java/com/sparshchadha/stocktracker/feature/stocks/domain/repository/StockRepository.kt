package com.sparshchadha.stocktracker.feature.stocks.domain.repository

import com.sparshchadha.stocktracker.core.common.utils.UiState
import com.sparshchadha.stocktracker.feature.stocks.data.remote.dto.StockChartResponse

interface StockRepository {
    suspend fun fetchStockDetailsIdentifier(
        identifier: String,
//        startFromEpoch: Long,
//        endEpoch: Long
    ): UiState<StockChartResponse>
}