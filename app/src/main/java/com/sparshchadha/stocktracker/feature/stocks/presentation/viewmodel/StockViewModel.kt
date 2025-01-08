package com.sparshchadha.stocktracker.feature.stocks.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sparshchadha.stocktracker.core.common.utils.UiState
import com.sparshchadha.stocktracker.feature.stocks.data.remote.dto.GetStockChartResponse
import com.sparshchadha.stocktracker.feature.stocks.domain.repository.StockRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StockViewModel @Inject constructor(
    private val stockRepository: StockRepository
) : ViewModel() {
    private var startFromEpoch: Long = 0
    private var endEpoch: Long = System.currentTimeMillis()

    private val _stockDetail = MutableStateFlow<UiState<GetStockChartResponse>?>(null)
    val stockDetail = _stockDetail.asStateFlow()

    fun setStartFromEpoch(startFromEpoch: Long) {
        this.startFromEpoch = startFromEpoch
    }

    fun setEndEpoch(endEpoch: Long) {
        this.endEpoch = endEpoch
    }

    fun getStockChart(identifier: String) {
        viewModelScope.launch {
            _stockDetail.value = stockRepository.fetchStockDetailsIdentifier(identifier, startFromEpoch, endEpoch)
        }
    }
}