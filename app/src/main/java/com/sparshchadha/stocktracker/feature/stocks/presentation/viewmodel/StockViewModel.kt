package com.sparshchadha.stocktracker.feature.stocks.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sparshchadha.stocktracker.core.common.utils.EpochTimeHelper
import com.sparshchadha.stocktracker.core.common.utils.TimeRange
import com.sparshchadha.stocktracker.core.common.utils.UiState
import com.sparshchadha.stocktracker.feature.stocks.data.remote.dto.StockChartResponse
import com.sparshchadha.stocktracker.feature.stocks.data.remote.dto.StockFundamentalsResponse
import com.sparshchadha.stocktracker.feature.stocks.domain.repository.StockRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StockViewModel @Inject constructor(
    private val stockRepository: StockRepository
) : ViewModel() {

    private val _stockChartData = MutableStateFlow<UiState<StockChartResponse>?>(null)
    val stockChartData = _stockChartData.asStateFlow()

    private val _selectedTimeRange = MutableStateFlow(TimeRange.DAY_1)
    val selectedTimeRange = _selectedTimeRange.asStateFlow()

    private val _stockFundamentalsData = MutableStateFlow<UiState<StockFundamentalsResponse>?>(null)
    val stockFundamentalsData = _stockFundamentalsData.asStateFlow()

    fun getStockDetails(identifier: String, exchange: String) {
        _stockChartData.value = UiState.Loading
        _stockFundamentalsData.value = UiState.Loading

        viewModelScope.launch {
            val startAndEndTimeEpoch = EpochTimeHelper.getEpochTimesForPeriod(
                exchange = exchange,
                period = TimeRange.DAY_1.dateStr
            )

            val stockChartData = async {
                stockRepository.getStockChartDetails(
                    identifier,
                    -1,
                    -1,
                    getInterval()
                )
            }

            val stockFundamentalsData = async {
                stockRepository.getStockFundamentals(
                    identifier,
                    startFromEpoch = startAndEndTimeEpoch.first,
                    endEpoch = startAndEndTimeEpoch.second
                )
            }

            _stockChartData.value = stockChartData.await()
            _stockFundamentalsData.value = stockFundamentalsData.await()
        }
    }

    fun getStockDetailsFromInterval(identifier: String) {
        val startEpoch = EpochTimeHelper.subtractTimeRangeFromEpoch(_selectedTimeRange.value)

        viewModelScope.launch {
            _stockChartData.value = stockRepository.getStockChartDetails(
                identifier = identifier,
                startFromEpoch = startEpoch,
                endEpoch = System.currentTimeMillis(),
                interval = getInterval()
            )
        }
    }

    private fun getInterval(): String {
//        Valid intervals: [1m, 2m, 5m, 15m, 30m, 60m, 90m, 1h, 1d, 5d, 1wk, 1mo, 3mo]"
        return when (this._selectedTimeRange.value) {
            TimeRange.DAY_1 -> {
                "1m"
            }
            TimeRange.WEEK_1 -> {
                "1m"
            }
            TimeRange.MONTH_1 -> {
                "1d"
            }
            TimeRange.YEAR_1 -> {
                "1d"
            }
            TimeRange.YEAR_2 -> {
                "5d"
            }
            TimeRange.YEAR_5 -> {
                "1wk"
            }
            TimeRange.ALL -> {
                "1mo"
            }
        }
    }

    fun updateSelectedTimeRange(timeRange: TimeRange) {
        if (timeRange != this._selectedTimeRange.value) {
            this._selectedTimeRange.value = timeRange
        }
    }
}