package com.sparshchadha.stocktracker.core.di

import android.content.Context
import androidx.room.Room
import com.sparshchadha.stocktracker.core.storage.room.CashCloudDB
import com.sparshchadha.stocktracker.feature.search.data.local.room.dao.SearchDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object StorageProvider {

    /**
     * Provides an instance of [CashCloudDB]
     * */
    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context,
    ): CashCloudDB {
        return Room.databaseBuilder(
            context,
            CashCloudDB::class.java,
            CashCloudDB.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    // region DAO
    @Singleton
    @Provides
    fun provideSearchDao(
        database: CashCloudDB
    ): SearchDao {
        return database.searchDao()
    }
    // endregion
}