package com.sparshchadha.stocktracker.feature.search.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sparshchadha.stocktracker.core.common.utils.UiState
import com.sparshchadha.stocktracker.feature.search.data.remote.dto.Quote
import com.sparshchadha.stocktracker.feature.search.data.remote.dto.SecuritySearchResponse
import com.sparshchadha.stocktracker.feature.search.domain.entity.SearchHistoryEntity
import com.sparshchadha.stocktracker.feature.search.domain.repository.SearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchRepository: SearchRepository
) : ViewModel() {
    private var _searchQuery: String = ""

    private val _securitySearchResponse = MutableStateFlow<UiState<SecuritySearchResponse>?>(null)
    val securitySearchResponse = _securitySearchResponse.asStateFlow()

    private val _searchHistory = MutableStateFlow<List<SearchHistoryEntity>?>(null)
    val searchHistory = _searchHistory.asStateFlow()

    private var debouncingJob: Job? = null

    private fun searchSecurities() {
        debouncingJob?.cancel() // Cancel any ongoing debouncing job.

        debouncingJob = viewModelScope.launch {
            delay(800L) // Delay for debouncing.
            _securitySearchResponse.value = UiState.Loading // Indicate loading state.
            val result = searchRepository.searchSecurities(_searchQuery) // Perform the search.
            _securitySearchResponse.value = result // Update with the result.
        }
    }


    fun deleteSearchHistoryItem(searchHistoryEntity: SearchHistoryEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            searchRepository.deleteSearchEntity(searchHistoryEntity)
        }
    }

    fun updateSearchQueryAndSearchForSecurity(searchQuery: String) {
        if (searchQuery.trim() != this._searchQuery) {
            this._searchQuery = searchQuery
            searchSecurities()
        }
    }

    fun insertInSearchHistory(quote: Quote) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                searchRepository.insertSearchEntity(
                    SearchHistoryEntity(
                        symbol = quote.symbol,
                        shortName = quote.shortname,
                        searchedAt = System.currentTimeMillis(),
                        exchDisp = quote.exchDisp,
                        exchange = quote.exchange
                    )
                )
            } catch (_: Exception) {

            }
        }
    }

    private fun getSearchHistory() {
        viewModelScope.launch(Dispatchers.IO) {
            searchRepository.getSearchHistory().collectLatest {
                _searchHistory.value = it
            }
        }
    }

    init {
        getSearchHistory()
    }
}