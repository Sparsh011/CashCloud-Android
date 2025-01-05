package com.sparshchadha.stocktracker.feature.search.data.remote.dto

data class ResearchReport(
    val id: String,
    val provider: String,
    val reportDate: Long,
    val reportHeadline: String
)