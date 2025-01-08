package com.sparshchadha.stocktracker.feature.stocks.domain.repository

import com.sparshchadha.stocktracker.core.common.utils.UiState
import com.sparshchadha.stocktracker.feature.stocks.data.remote.dto.GetStockChartResponse

interface StockRepository {
    suspend fun fetchStockDetailsIdentifier(
        identifier: String,
        startFromEpoch: Long,
        endEpoch: Long
    ): UiState<GetStockChartResponse>
}