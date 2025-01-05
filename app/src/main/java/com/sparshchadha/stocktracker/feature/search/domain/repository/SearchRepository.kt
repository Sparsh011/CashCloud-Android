package com.sparshchadha.stocktracker.feature.search.domain.repository

import com.sparshchadha.stocktracker.core.common.utils.UiState
import com.sparshchadha.stocktracker.feature.search.data.remote.dto.SecuritySearchResponse
import com.sparshchadha.stocktracker.feature.search.domain.entity.SearchHistoryEntity
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    fun getSearchHistory(): Flow<List<SearchHistoryEntity>>
    fun insertSearchEntity(searchHistoryEntity: SearchHistoryEntity)
    fun deleteSearchEntity(searchHistoryEntity: SearchHistoryEntity)

    suspend fun searchSecurities(searchQuery: String): UiState<SecuritySearchResponse>
}