package com.sparshchadha.stocktracker.feature.search.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    indices = [androidx.room.Index(value = ["symbol"], unique = true)]
)
data class SearchHistoryEntity(
    @PrimaryKey val id: Long? = null,
    val symbol: String,
    val shortName: String,
    val searchedAt: Long,
    val exchDisp: String,
    val exchange: String,
)
