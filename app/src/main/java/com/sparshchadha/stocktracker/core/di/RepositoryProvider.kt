package com.sparshchadha.stocktracker.core.di

import com.sparshchadha.stocktracker.core.network.YahooAPI
import com.sparshchadha.stocktracker.feature.search.data.local.room.dao.SearchDao
import com.sparshchadha.stocktracker.feature.search.data.repository.SearchRepositoryImpl
import com.sparshchadha.stocktracker.feature.search.domain.repository.SearchRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryProvider {

    @Singleton
    @Provides
    fun provideSearchRepository(
        searchDao: SearchDao,
        yahooAPI: YahooAPI
    ): SearchRepository {
        return SearchRepositoryImpl(searchDao = searchDao, yahooAPI = yahooAPI)
    }
}