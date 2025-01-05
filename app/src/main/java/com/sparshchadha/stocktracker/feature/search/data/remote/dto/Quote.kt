package com.sparshchadha.stocktracker.feature.search.data.remote.dto

data class Quote(
    val dispSecIndFlag: Boolean,
    val exchDisp: String,
    val exchange: String,
    val index: String,
    val industry: String,
    val industryDisp: String,
    val isYahooFinance: Boolean,
    val longname: String,
    val name: String,
    val nameChangeDate: String,
    val permalink: String,
    val prevName: String,
    val quoteType: String,
    val score: Double,
    val sector: String,
    val sectorDisp: String,
    val shortname: String,
    val symbol: String,
    val typeDisp: String
)