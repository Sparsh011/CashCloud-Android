package com.sparshchadha.stocktracker.feature.stocks.presentation.compose.stock_details

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sparshchadha.stocktracker.core.common.extensions.toCurrencyString
import com.sparshchadha.stocktracker.core.common.presentation.CashCloudTopBar
import com.sparshchadha.stocktracker.core.common.utils.TimeRange
import com.sparshchadha.stocktracker.core.common.utils.UiState
import com.sparshchadha.stocktracker.core.theme.Dimensions
import com.sparshchadha.stocktracker.core.theme.FontSizes
import com.sparshchadha.stocktracker.core.theme.primaryRed
import com.sparshchadha.stocktracker.core.theme.primaryAppBackground
import com.sparshchadha.stocktracker.core.theme.primaryPurple
import com.sparshchadha.stocktracker.core.theme.primaryTextColor
import com.sparshchadha.stocktracker.feature.stocks.data.remote.dto.StockChartResponse

@Composable
fun StockDetailScreen(
    stockDetails: UiState<StockChartResponse>?,
    identifier: String,
    onBackPress: () -> Unit,
    onTimeUnitChange: (TimeRange) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(primaryAppBackground)
    ) {
        CashCloudTopBar(identifier, onBackPress = onBackPress)

        when (stockDetails) {
            UiState.Loading, is UiState.LoadingWithData -> {
                CircularProgressIndicator()
            }

            is UiState.Success -> {
                StockDetails(stockDetails.data, onTimeUnitChange)
            }

            else -> {
                // Show error
            }
        }
    }
}

@Composable
private fun StockDetails(data: StockChartResponse, onTimeUnitChange: (TimeRange) -> Unit) {
    val stockMeta by remember {
        mutableStateOf(data.chart.result[0].meta)
    }

    Column(
        modifier = Modifier.verticalScroll(rememberScrollState())
    ) {
        TimeRangeRow(onTimeRangeClick = onTimeUnitChange)

        Spacer(Modifier.height(Dimensions.largePadding()))

        Text(
            "Performance",
            color = primaryTextColor,
            fontWeight = FontWeight.Bold,
            fontSize = FontSizes.largeNonScaledFontSize().value.sp,
            modifier = Modifier.padding(horizontal = Dimensions.extraSmallPadding())
        )

        Spacer(Modifier.height(Dimensions.largePadding()))

        if (stockMeta.regularMarketDayLow != null && stockMeta.regularMarketDayHigh != null) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    "Today's Low",
                    color = primaryTextColor,
                    fontWeight = FontWeight.Bold,
                    fontSize = FontSizes.mediumFontSize().value.sp,
                    modifier = Modifier.padding(horizontal = Dimensions.extraSmallPadding())
                )

                Text(
                    "Today's High",
                    color = primaryTextColor,
                    fontWeight = FontWeight.Bold,
                    fontSize = FontSizes.mediumFontSize().value.sp,
                    modifier = Modifier.padding(horizontal = Dimensions.extraSmallPadding())
                )
            }

            Spacer(Modifier.height(Dimensions.smallPadding()))

            PerformanceIndicator(
                regularMarketPrice = stockMeta.regularMarketPrice,
                marketLowPrice = stockMeta.regularMarketDayLow!!,
                marketHighPrice = stockMeta.regularMarketDayHigh!!,
                currency = stockMeta.currency
            )

            Spacer(Modifier.height(Dimensions.largePadding()))
        }

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                "52 Week Low",
                color = primaryTextColor,
                fontWeight = FontWeight.Bold,
                fontSize = FontSizes.mediumFontSize().value.sp,
                modifier = Modifier.padding(horizontal = Dimensions.extraSmallPadding())
            )

            Text(
                "52 Week High",
                color = primaryTextColor,
                fontWeight = FontWeight.Bold,
                fontSize = FontSizes.mediumFontSize().value.sp,
                modifier = Modifier.padding(horizontal = Dimensions.extraSmallPadding())
            )
        }

        Spacer(Modifier.height(Dimensions.smallPadding()))

        PerformanceIndicator(
            regularMarketPrice = stockMeta.regularMarketPrice,
            marketLowPrice = stockMeta.fiftyTwoWeekLow,
            marketHighPrice = stockMeta.fiftyTwoWeekHigh,
            currency = stockMeta.currency
        )

    }
}

@Composable
private fun PerformanceIndicator(
    regularMarketPrice: Double,
    marketLowPrice: Double,
    marketHighPrice: Double,
    currency: String
) {
    var isAnimationComplete by rememberSaveable {
        mutableStateOf(false)
    }

    // Find the difference between regular price and high & low prices. Then divide the loweToRegularDiff by scaleBound because our line graph starts from left to right.
    val lowToRegularDiff by remember { mutableFloatStateOf(regularMarketPrice.toFloat() - marketLowPrice.toFloat()) }
    val highToRegularDiff by remember { mutableFloatStateOf(marketHighPrice.toFloat() - regularMarketPrice.toFloat()) }
    val scaleBound by remember {
        mutableFloatStateOf(lowToRegularDiff + highToRegularDiff)
    }

    LaunchedEffect(Unit) {
        isAnimationComplete = true
    }

    val lineGraphPositionPercent by remember {
        mutableFloatStateOf(
            lowToRegularDiff / scaleBound
        )
    }

    var priceTextWidth by remember { mutableIntStateOf(0) }

    val offsetXCurrentPrice =
        LocalConfiguration.current.screenWidthDp.toFloat() * (lineGraphPositionPercent) - (priceTextWidth / 2)

    PriceText(
        price = regularMarketPrice.toFloat(),
        modifier = Modifier
            .offset(x = offsetXCurrentPrice.dp)
            .padding(horizontal = Dimensions.extraSmallPadding())
            .onSizeChanged {
                priceTextWidth = it.width
            },
        textAlign = TextAlign.Unspecified,
        currency = currency
    )

    val progress by animateFloatAsState(
        targetValue = if (isAnimationComplete) (lineGraphPositionPercent) else 0f,
        animationSpec = tween(450),
        label = ""
    )

    LinearProgressIndicator(
        progress = {
            progress
        },
        color = primaryPurple,
        trackColor = Color.LightGray,
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = Dimensions.smallPadding(),
                vertical = Dimensions.extraSmallPadding()
            )
    )

    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        PriceText(
            price = marketLowPrice.toFloat(),
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = Dimensions.extraSmallPadding()),
            textAlign = TextAlign.Start,
            currency = currency
        )

        PriceText(
            price = marketHighPrice.toFloat(),
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = Dimensions.extraSmallPadding()),

            textAlign = TextAlign.End,
            currency = currency
        )
    }
}

@Composable
private fun PriceText(
    modifier: Modifier,
    price: Float,
    textAlign: TextAlign,
    currency: String
) {
    Text(
        text = price.toCurrencyString(currency),
        textAlign = textAlign,
        modifier = modifier,
        color = primaryTextColor,
        fontSize = FontSizes.smallNonScaledFontSize()
    )
}
