package com.sparshchadha.stocktracker.feature.search.data.local.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.sparshchadha.stocktracker.feature.search.domain.entity.SearchHistoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SearchDao {
    @Insert
    fun insert(searchHistoryEntity: SearchHistoryEntity)

    @Delete
    fun delete(searchHistoryEntity: SearchHistoryEntity)

    @Query("SELECT * FROM SearchHistoryEntity LIMIT 10")
    fun getSearchHistory(): Flow<List<SearchHistoryEntity>>
}