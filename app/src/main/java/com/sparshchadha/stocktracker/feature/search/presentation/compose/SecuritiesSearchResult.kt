package com.sparshchadha.stocktracker.feature.search.presentation.compose

import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.sparshchadha.stocktracker.R
import com.sparshchadha.stocktracker.core.theme.Dimensions
import com.sparshchadha.stocktracker.core.theme.FontSizes
import com.sparshchadha.stocktracker.core.theme.primaryTextColor
import com.sparshchadha.stocktracker.feature.search.data.remote.dto.Quote

@Composable
fun SecuritiesSearchResult(
    securitiesList: List<Quote>,
    onItemClick: (String) -> Unit,
    onUpdateSearchHistory: (Quote) -> Unit
) {

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(Dimensions.smallPadding())
    ) {
        item {
            Text(
                "Showing ${securitiesList.size} results",
                color = primaryTextColor,
                modifier = Modifier.padding(Dimensions.smallPadding()),
                fontSize = FontSizes.mediumNonScaledFontSize()
            )
        }

        items(securitiesList) {
            SecurityItem(it, onItemClick, onUpdateSearchHistory)
        }
    }
}

@Composable
private fun SecurityItem(
    quote: Quote,
    onItemClick: (String) -> Unit,
    onUpdateSearchHistory: (Quote) -> Unit
) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onItemClick(quote.symbol)
                onUpdateSearchHistory(quote)
            }
            .padding(vertical = Dimensions.mediumPadding()),
        verticalAlignment = Alignment.CenterVertically
    ){
        Column (
            modifier = Modifier.weight(0.9f)
        ) {
            Text(
                text = quote.shortname,
                color = primaryTextColor,
                fontSize = FontSizes.mediumFontSize().value.sp,
                modifier = Modifier.padding(horizontal = Dimensions.smallPadding()),
                fontWeight = FontWeight.Bold
            )

            Spacer(Modifier.height(Dimensions.extraSmallPadding()))

            Text(
                text = quote.symbol + "\t" + quote.exchange + "\t" + quote.exchDisp,
                color = Color.Gray,
                fontSize = FontSizes.smallFontSize().value.sp,
                modifier = Modifier
                    .padding(horizontal = Dimensions.smallPadding())
                    .basicMarquee(5),
                maxLines = 1
            )
        }

        Icon(
            ImageVector.vectorResource(R.drawable.ic_arrow_north_east),
            contentDescription = null,
            tint = primaryTextColor
        )
    }

}

val stockExchangeToCurrencyMap = mapOf(
    "NSE" to "INR", // National Stock Exchange - India
    "BSE" to "INR", // Bombay Stock Exchange - India
    "NYSE" to "USD", // New York Stock Exchange - United States
    "NASDAQ" to "USD", // NASDAQ - United States
    "LSE" to "GBP", // London Stock Exchange - United Kingdom
    "JPX" to "JPY", // Japan Exchange Group - Japan
    "HKEX" to "HKD", // Hong Kong Stock Exchange - Hong Kong
    "SSE" to "CNY", // Shanghai Stock Exchange - China
    "SZSE" to "CNY", // Shenzhen Stock Exchange - China
    "ASX" to "AUD", // Australian Securities Exchange - Australia
    "TSX" to "CAD", // Toronto Stock Exchange - Canada
    "DAX" to "EUR", // Frankfurt Stock Exchange - Germany
    "CAC" to "EUR", // Euronext Paris - France
    "BOVESPA" to "BRL", // B3 (Brazil Stock Exchange) - Brazil
    "KRX" to "KRW", // Korea Exchange - South Korea
    "SGX" to "SGD", // Singapore Exchange - Singapore
    "TASE" to "ILS", // Tel Aviv Stock Exchange - Israel
    "MOEX" to "RUB", // Moscow Exchange - Russia
    "JSE" to "ZAR" // Johannesburg Stock Exchange - South Africa
)