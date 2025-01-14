package com.sparshchadha.stocktracker.feature.stocks.presentation.compose.stock_details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sparshchadha.stocktracker.core.theme.Dimensions
import com.sparshchadha.stocktracker.core.theme.FontSizes
import com.sparshchadha.stocktracker.core.theme.primaryTextColor
import com.sparshchadha.stocktracker.feature.stocks.data.remote.dto.ResultTimeSeries
import com.sparshchadha.stocktracker.feature.stocks.data.remote.dto.StockFundamentalsResponse


@Composable
fun FundamentalDetails(data: StockFundamentalsResponse) {
    val resultList = data.timeseries.result
    StockFundamentalsCard(results = resultList)
}

@Composable
fun StockFundamentalsCard(results: List<ResultTimeSeries>) {
    val trailingMarketCap = findFirstNonNull(results) { it.quarterlyMarketCap?.firstOrNull()?.reportedValue?.fmt }
    val trailingPeRatio = findFirstNonNull(results) { it.quarterlyPeRatio?.firstOrNull()?.reportedValue?.fmt }
    val trailingPbRatio = findFirstNonNull(results) { it.quarterlyPbRatio?.firstOrNull()?.reportedValue?.fmt }
    val currency = findFirstNonNull(results) { it.quarterlyMarketCap?.firstOrNull()?.currencyCode }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(Dimensions.mediumPadding())
    ) {
        Text(
            text = "Stock Fundamentals",
            fontWeight = FontWeight.Bold,
            color = primaryTextColor,
            fontSize = FontSizes.mediumFontSize().value.sp
        )
        Spacer(modifier = Modifier.height(Dimensions.mediumPadding()))

        InfoRow(label = "Market Cap", value = trailingMarketCap + " " + currency)

        InfoRow(label = "PE Ratio", value = trailingPeRatio)

        InfoRow(label = "PB Ratio", value = trailingPbRatio)
    }
}

@Composable
fun InfoRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            fontWeight = FontWeight.Bold,
            color = primaryTextColor,
            fontSize = FontSizes.mediumNonScaledFontSize()
        )
        Text(text = value, color = Color.Gray, fontSize = FontSizes.mediumNonScaledFontSize())
    }
}

fun <T> findFirstNonNull(results: List<ResultTimeSeries>, selector: (ResultTimeSeries) -> T?): String {
    for (result in results) {
        selector(result)?.let { return it.toString() }
    }
    return "NA" // Fallback if no non-null value is found
}
