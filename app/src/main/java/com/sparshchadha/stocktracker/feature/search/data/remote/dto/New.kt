package com.sparshchadha.stocktracker.feature.search.data.remote.dto

data class New(
    val link: String,
    val providerPublishTime: Int,
    val publisher: String,
    val relatedTickers: List<String>,
    val thumbnail: Thumbnail,
    val title: String,
    val type: String,
    val uuid: String
)