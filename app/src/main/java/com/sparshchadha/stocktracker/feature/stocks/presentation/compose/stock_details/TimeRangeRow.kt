package com.sparshchadha.stocktracker.feature.stocks.presentation.compose.stock_details

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.sparshchadha.stocktracker.core.common.utils.TimeRange
import com.sparshchadha.stocktracker.core.theme.Dimensions
import com.sparshchadha.stocktracker.core.theme.FontSizes
import com.sparshchadha.stocktracker.core.theme.primaryTextColor

@Composable
fun TimeRangeRow(
    onTimeRangeClick: (TimeRange) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(Dimensions.extraSmallPadding()),
        verticalAlignment = Alignment.CenterVertically
    ) {
        for (timeRange in TimeRange.entries) {
            TimeRangeItem(
                modifier = Modifier
                    .weight(1f)
                    .padding(Dimensions.extraSmallPadding())
                    .clickable {
                        onTimeRangeClick(timeRange)
                    },
                dateStr = timeRange.dateStr,
            )
        }
    }
}

@Composable
private fun TimeRangeItem(
    modifier: Modifier,
    dateStr: String,
) {
    Text(
        text = dateStr,
        modifier = modifier,
        color = primaryTextColor,
        fontSize = FontSizes.smallNonScaledFontSize()
    )
}
