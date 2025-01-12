package com.sparshchadha.stocktracker.feature.stocks.presentation.compose.stock_details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sparshchadha.stocktracker.core.common.presentation.CashCloudTopBar
import com.sparshchadha.stocktracker.core.common.presentation.SecurityDetailLoadingShimmer
import com.sparshchadha.stocktracker.core.common.utils.TimeRange
import com.sparshchadha.stocktracker.core.common.utils.UiState
import com.sparshchadha.stocktracker.core.theme.Dimensions
import com.sparshchadha.stocktracker.core.theme.primaryAppBackground
import com.sparshchadha.stocktracker.feature.stocks.data.remote.dto.StockChartResponse

@Composable
fun StockDetailScreen(
    stockDetails: UiState<StockChartResponse>?,
    identifier: String,
    onBackPress: () -> Unit,
    onTimeUnitChange: (TimeRange) -> Unit,
    selectedTimeRange: TimeRange
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(primaryAppBackground)
    ) {
        CashCloudTopBar(identifier, onBackPress = onBackPress)

        when (stockDetails) {
            UiState.Loading, is UiState.LoadingWithData -> {
                SecurityDetailLoadingShimmer()
            }

            is UiState.Success -> {
                StockDetails(stockDetails.data, onTimeUnitChange, selectedTimeRange)
            }

            else -> {
                // Show error
            }
        }
    }
}

@Composable
private fun StockDetails(data: StockChartResponse, onTimeUnitChange: (TimeRange) -> Unit, selectedTimeRange: TimeRange) {
    Column(
        modifier = Modifier.verticalScroll(rememberScrollState())
    ) {
        TimeRangeRow(onTimeRangeClick = onTimeUnitChange, selectedTimeRange)

        Spacer(Modifier.height(Dimensions.largePadding()))

        PerformanceTab(data.chart.result[0].meta)


    }
}
