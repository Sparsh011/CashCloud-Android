package com.sparshchadha.stocktracker.feature.search.presentation.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.sparshchadha.stocktracker.core.theme.primaryAppBackground
import com.sparshchadha.stocktracker.feature.search.data.remote.dto.Quote
import com.sparshchadha.stocktracker.feature.search.domain.entity.SearchHistoryEntity

@Composable
fun SearchScreen(
    onSearch: (String) -> Unit,
    shouldShowLoader: Boolean,
    securitiesList: List<Quote>,
    showHistory: Boolean,
    searchHistory: List<SearchHistoryEntity>,
    onDeleteSearchHistoryItem: (SearchHistoryEntity) -> Unit,
    onItemClick: (symbol: String, exchangeDisp: String) -> Unit,
    onUpdateSearchHistory: (Quote) -> Unit,
    onBackPress: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(primaryAppBackground),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SecuritiesSearchBar(onSearch = onSearch, onBackPress = onBackPress)

        if (showHistory) {
            SearchHistory(searchHistory = searchHistory, onDeleteClick = onDeleteSearchHistoryItem, onItemClick = onItemClick)
        } else if (shouldShowLoader) {
            CircularProgressIndicator()
        } else {
            SecuritiesSearchResult(securitiesList = securitiesList, onItemClick = onItemClick, onUpdateSearchHistory = onUpdateSearchHistory)
        }
    }
}
