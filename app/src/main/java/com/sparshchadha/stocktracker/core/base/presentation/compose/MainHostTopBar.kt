package com.sparshchadha.stocktracker.core.base.presentation.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.sparshchadha.stocktracker.core.theme.Dimensions
import com.sparshchadha.stocktracker.core.theme.FontSizes
import com.sparshchadha.stocktracker.core.theme.primaryTextColor

@Composable
fun MainHostFragmentTopBar(
    onSearchIconClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = Dimensions.statusBarPadding()),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            "CashCloud",
            color = primaryTextColor,
            modifier = Modifier.weight(0.8f)
                .padding(Dimensions.smallPadding()),
            fontSize = FontSizes.extraLargeFontSize().value.sp,
            fontWeight = FontWeight.Bold
        )
        Icon(
            imageVector = Icons.Filled.Search,
            contentDescription = null,
            modifier = Modifier
                .weight(0.2f)
                .clickable { onSearchIconClick() },
            tint = primaryTextColor
        )
    }
}