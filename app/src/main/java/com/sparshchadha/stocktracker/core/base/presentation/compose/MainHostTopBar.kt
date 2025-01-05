package com.sparshchadha.stocktracker.core.base.presentation.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.sparshchadha.stocktracker.core.theme.primaryTextColor

@Composable
fun MainHostFragmentTopBar(
    onSearchIconClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text("CashCloud", color = primaryTextColor, modifier = Modifier.weight(0.8f))
        Icon(
            imageVector = Icons.Filled.Search,
            contentDescription = null,
            modifier = Modifier
                .weight(0.2f)
                .clickable { onSearchIconClick() })
    }
}