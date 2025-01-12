package com.sparshchadha.stocktracker.feature.stocks.presentation.compose.stock_details

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sparshchadha.stocktracker.R
import com.sparshchadha.stocktracker.core.theme.Dimensions
import com.sparshchadha.stocktracker.core.theme.FontSizes
import com.sparshchadha.stocktracker.core.theme.primaryPurple
import com.sparshchadha.stocktracker.core.theme.primaryRed
import com.sparshchadha.stocktracker.core.theme.primaryTextColor

@Composable
fun ErrorScreen(
    errorMessage: String,
    onRetryClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(Dimensions.largePadding()),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(Dimensions.mediumPadding())
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_error),
                contentDescription = "Error Icon",
                tint = primaryTextColor,
                modifier = Modifier.size(Dimensions.errorIconSize())
            )

            Text(
                text = errorMessage,
                color = primaryTextColor,
                fontSize = FontSizes.mediumNonScaledFontSize(),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            Button(
                onClick = onRetryClick,
                colors = ButtonDefaults.buttonColors(containerColor = primaryPurple),
                modifier = Modifier
                    .padding(horizontal = Dimensions.extraLargePadding())
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Try Again",
                    color = primaryTextColor,
                    fontSize = FontSizes.mediumNonScaledFontSize(),
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
