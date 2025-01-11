package com.sparshchadha.stocktracker.feature.stocks.data.repository

import com.sparshchadha.stocktracker.core.base.domain.BaseRepository
import com.sparshchadha.stocktracker.core.common.utils.UiState
import com.sparshchadha.stocktracker.core.common.utils.toUiState
import com.sparshchadha.stocktracker.core.network.YahooAPI
import com.sparshchadha.stocktracker.feature.stocks.data.remote.dto.StockChartResponse
import com.sparshchadha.stocktracker.feature.stocks.domain.repository.StockRepository

class StockRepositoryImpl(
    private val yahooAPI: YahooAPI
) : StockRepository, BaseRepository() {

    override suspend fun fetchStockDetailsIdentifier(
        identifier: String,
//        startFromEpoch: Long,
//        endEpoch: Long
    ): UiState<StockChartResponse> {
        return safeApiCall {
            yahooAPI.getStockChart(
                identifier,
//                startFromEpoch,
//                endEpoch
            )
        }.toUiState()
    }
}