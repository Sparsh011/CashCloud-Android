package com.sparshchadha.stocktracker.feature.stocks.presentation.compose.stock_details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.sparshchadha.stocktracker.R
import com.sparshchadha.stocktracker.core.theme.Dimensions
import com.sparshchadha.stocktracker.core.theme.FontSizes
import com.sparshchadha.stocktracker.core.theme.primaryPurple
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
