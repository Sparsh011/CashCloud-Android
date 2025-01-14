package com.sparshchadha.stocktracker.feature.search.presentation.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.sparshchadha.stocktracker.R
import com.sparshchadha.stocktracker.core.theme.Dimensions
import com.sparshchadha.stocktracker.core.theme.FontSizes
import com.sparshchadha.stocktracker.core.theme.primaryTextColor
import com.sparshchadha.stocktracker.feature.search.domain.entity.SearchHistoryEntity

@Composable
fun SearchHistory(
    searchHistory: List<SearchHistoryEntity>,
    onDeleteClick: (SearchHistoryEntity) -> Unit,
    onItemClick: (symbol: String, exchange: String) -> Unit,
) {

    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.stocks))
    val progress by animateLottieCompositionAsState(composition)

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(Dimensions.smallPadding())
    ) {
        item {
            if (searchHistory.isNotEmpty()) {
                Text(
                    text = "Recent Search History",
                    color = primaryTextColor,
                    fontSize = FontSizes.mediumFontSize().value.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(Dimensions.smallPadding()),
                    textAlign = TextAlign.Start
                )
            } else {
                LottieAnimation(
                    composition = composition,
                    progress = { progress },
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(Dimensions.largePadding())
                )
            }
        }


        items(searchHistory) {
            SearchHistoryItem(
                item = it,
                onDeleteClick = onDeleteClick,
                onItemClick = onItemClick
            )
        }
    }
}

@Composable
private fun SearchHistoryItem(
    onDeleteClick: (SearchHistoryEntity) -> Unit,
    onItemClick: (symbol: String, exchange: String) -> Unit,
    item: SearchHistoryEntity
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = Dimensions.mediumPadding()),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Outlined.Delete,
            contentDescription = null,
            tint = Color.Red,
            modifier = Modifier
                .weight(0.1f)
                .clickable { onDeleteClick(item) }
        )

        Column(
            modifier = Modifier
                .weight(0.8f)
                .clickable {
                    onItemClick(item.symbol, item.exchDisp)
                }
        ) {
            Text(
                text = item.shortName,
                color = primaryTextColor,
                fontSize = FontSizes.mediumFontSize().value.sp,
                modifier = Modifier.padding(start = Dimensions.extraSmallPadding()),
                fontWeight = FontWeight.Bold
            )

            Spacer(Modifier.height(Dimensions.extraSmallPadding()))

            Text(
                text = item.symbol + "\t" + item.exchange + "\t" + item.exchDisp,
                color = Color.Gray,
                fontSize = FontSizes.smallFontSize().value.sp,
                modifier = Modifier.padding(start = Dimensions.extraSmallPadding()),
            )
        }


        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_arrow_north_east),
            contentDescription = null,
            tint = primaryTextColor,
            modifier = Modifier.clickable {
                onItemClick(item.symbol, item.exchange)
            }
        )
    }
}
