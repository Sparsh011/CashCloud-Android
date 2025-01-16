package com.sparshchadha.stocktracker.feature.stocks.presentation.compose.stock_details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sparshchadha.stocktracker.core.common.presentation.CashCloudTopBar
import com.sparshchadha.stocktracker.core.common.presentation.SecurityDetailLoadingShimmer
import com.sparshchadha.stocktracker.core.common.utils.TimeRange
import com.sparshchadha.stocktracker.core.common.utils.UiState
import com.sparshchadha.stocktracker.core.theme.Dimensions
import com.sparshchadha.stocktracker.core.theme.primaryAppBackground
import com.sparshchadha.stocktracker.feature.stocks.data.remote.dto.StockChartResponse
import com.sparshchadha.stocktracker.feature.stocks.data.remote.dto.StockFundamentalsResponse

@Composable
fun StockDetailScreen(
    stockDetails: UiState<StockChartResponse>?,
    identifier: String,
    onBackPress: () -> Unit,
    onTimeUnitChange: (TimeRange) -> Unit,
    selectedTimeRange: TimeRange,
    stockFundamentals: UiState<StockFundamentalsResponse>?,
    onRetry: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(primaryAppBackground)
    ) {
        CashCloudTopBar(identifier, onBackPress = onBackPress)

        Column(
            modifier = Modifier.verticalScroll(rememberScrollState())
        ) {
            ChartDetails(stockDetails, onTimeUnitChange, selectedTimeRange, onRetry)

            Fundamentals(stockFundamentals, onRetry)
        }
    }
}

@Composable
fun Fundamentals(
    stockFundamentals: UiState<StockFundamentalsResponse>?,
    onRetry: () -> Unit
) {
    when (stockFundamentals) {
        is UiState.Error -> {
            ErrorScreen(stockFundamentals.error, onRetry)
        }
        is UiState.LoadingWithData, UiState.Loading -> {
            // Do nothing currently
        }
        is UiState.Success -> {
            FundamentalDetails(stockFundamentals.data)
        }
        else -> {
            // Ignore
        }
    }
}


@Composable
private fun ChartDetails(
    stockDetails: UiState<StockChartResponse>?,
    onTimeUnitChange: (TimeRange) -> Unit,
    selectedTimeRange: TimeRange,
    onRetry: () -> Unit
) {
    when (stockDetails) {
        UiState.Loading, is UiState.LoadingWithData -> {
            SecurityDetailLoadingShimmer()
        }

        is UiState.Success -> {
            StockDetails(stockDetails.data, onTimeUnitChange, selectedTimeRange)
        }

        is UiState.Error -> {
            ErrorScreen(stockDetails.error, onRetryClick = onRetry)
        }

        else -> {
            ErrorScreen(
                "Something Went Wrong! Could not fetch data, please try again.",
                onRetryClick = onRetry
            )
        }
    }
}

@Composable
private fun StockDetails(
    data: StockChartResponse,
    onTimeUnitChange: (TimeRange) -> Unit,
    selectedTimeRange: TimeRange
) {
    Column {
        TimeRangeRow(onTimeRangeClick = onTimeUnitChange, selectedTimeRange)

        Spacer(Modifier.height(Dimensions.largePadding()))

        PerformanceTab(data.chart.result[0].meta)
    }
}
