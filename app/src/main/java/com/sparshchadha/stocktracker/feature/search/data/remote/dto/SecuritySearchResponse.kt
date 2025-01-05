package com.sparshchadha.stocktracker.feature.search.data.remote.dto

data class SecuritySearchResponse(
    val count: Int,
    val explains: List<Any>,
    val lists: List<Any>,
    val nav: List<Any>,
    val news: List<New>,
    val quotes: List<Quote>,
    val researchReports: List<ResearchReport>,
    val screenerFieldResults: List<Any>,
    val timeTakenForAlgowatchlist: Int,
    val timeTakenForCrunchbase: Int,
    val timeTakenForCulturalAssets: Int,
    val timeTakenForNav: Int,
    val timeTakenForNews: Int,
    val timeTakenForPredefinedScreener: Int,
    val timeTakenForQuotes: Int,
    val timeTakenForResearchReports: Int,
    val timeTakenForScreenerField: Int,
    val timeTakenForSearchLists: Int,
    val totalTime: Int
)