package com.sparshchadha.stocktracker.feature.search.data.repository

import com.sparshchadha.stocktracker.core.base.domain.BaseRepository
import com.sparshchadha.stocktracker.core.common.utils.UiState
import com.sparshchadha.stocktracker.core.common.utils.toUiState
import com.sparshchadha.stocktracker.core.network.YahooAPI
import com.sparshchadha.stocktracker.feature.search.data.local.room.dao.SearchDao
import com.sparshchadha.stocktracker.feature.search.data.remote.dto.SecuritySearchResponse
import com.sparshchadha.stocktracker.feature.search.domain.entity.SearchHistoryEntity
import com.sparshchadha.stocktracker.feature.search.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow

class SearchRepositoryImpl(
    private val searchDao: SearchDao,
    private val yahooAPI: YahooAPI
): SearchRepository, BaseRepository() {
    override fun getSearchHistory(): Flow<List<SearchHistoryEntity>> {
        return searchDao.getSearchHistory()
    }

    override fun insertSearchEntity(searchHistoryEntity: SearchHistoryEntity) {
        searchDao.insert(searchHistoryEntity)
    }

    override fun deleteSearchEntity(searchHistoryEntity: SearchHistoryEntity) {
        searchDao.delete(searchHistoryEntity)
    }

    override suspend fun searchSecurities(searchQuery: String): UiState<SecuritySearchResponse> {
        return safeApiCall { yahooAPI.securitySearch(searchQuery) }.toUiState()
    }

}