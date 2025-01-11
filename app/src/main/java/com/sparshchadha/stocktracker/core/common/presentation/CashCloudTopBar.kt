package com.sparshchadha.stocktracker.core.common.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import com.sparshchadha.stocktracker.core.theme.Dimensions
import com.sparshchadha.stocktracker.core.theme.FontSizes
import com.sparshchadha.stocktracker.core.theme.primaryTextColor

@Composable
fun CashCloudTopBar(
    title: String,
    onBackPress: (() -> Unit),
    isTitleBold: Boolean = false
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = Dimensions.statusBarPadding())
            .padding(horizontal = Dimensions.extraSmallPadding())
            .height(Dimensions.topBarHeight()),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = null,
            modifier = Modifier
                .weight(0.15f)
                .clickable {
                    onBackPress()
                },
            tint = primaryTextColor
        )

        Text(
            text = title,
            textAlign = TextAlign.Start,
            fontSize = FontSizes.mediumFontSize().value.sp,
            fontWeight = if (isTitleBold) FontWeight.Bold else FontWeight.Normal,
            modifier = Modifier
                .fillMaxWidth()
                .padding(Dimensions.extraSmallPadding())
                .weight(1f),
            color = primaryTextColor,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}