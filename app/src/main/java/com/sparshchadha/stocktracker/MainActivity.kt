package com.sparshchadha.stocktracker

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.navigation.NavController
import androidx.navigation.createGraph
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.fragment
import com.google.gson.Gson
import com.sparshchadha.stocktracker.core.base.presentation.fragment.MainHostFragment
import com.sparshchadha.stocktracker.core.common.extensions.isDarkThemeOn
import com.sparshchadha.stocktracker.feature.search.presentation.fragment.SearchFragment
import com.sparshchadha.stocktracker.feature.stocks.presentation.fragment.StockDetailsFragment
import com.sparshchadha.stocktracker.navigation.CashCloudNavGraph

class MainActivity : AppCompatActivity() {
    private lateinit var parentNavController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        setWindowAttributes()

        setupNavGraph()
    }

    private fun setupNavGraph() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.mainHostFragmentContainer) as NavHostFragment
        parentNavController = navHostFragment.navController

        parentNavController.graph = parentNavController.createGraph(
            startDestination = CashCloudNavGraph.MainScreenRoutes.MAIN_HOST_SCREEN
        ) {
            fragment<StockDetailsFragment>(CashCloudNavGraph.MainScreenRoutes.STOCK_DETAILS_SCREEN) {
                label = "Stock Details"
            }
            fragment<MainHostFragment>(CashCloudNavGraph.MainScreenRoutes.MAIN_HOST_SCREEN) {
                label = "Main Host Fragment"
            }
            fragment<SearchFragment>(CashCloudNavGraph.MainScreenRoutes.SEARCH_SCREEN) {
                label = "Search Fragment"
            }
        }
    }

    private fun setWindowAttributes() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.app_container)) { _, insets ->
            insets
        }

        WindowCompat.getInsetsController(
            window,
            findViewById(R.id.app_container)
        ).isAppearanceLightStatusBars = !isDarkThemeOn()
    }

    private fun getStocks(): CharSequence {
        val json = this.assets.open("stocks.json").bufferedReader().use { it.readText() }
        val gson = Gson()
        val data: List<Map<String, String>> =
            gson.fromJson(json, List::class.java) as List<Map<String, String>>
        return data.toString()
    }
}