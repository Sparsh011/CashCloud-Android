package com.sparshchadha.stocktracker.core.storage.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sparshchadha.stocktracker.feature.search.data.local.room.dao.SearchDao
import com.sparshchadha.stocktracker.feature.search.domain.entity.SearchHistoryEntity

@Database(entities = [SearchHistoryEntity::class], version = 1)
abstract class CashCloudDB: RoomDatabase() {
    abstract fun searchDao(): SearchDao

    companion object {
        const val DATABASE_NAME = "cash_cloud_db"
    }
}