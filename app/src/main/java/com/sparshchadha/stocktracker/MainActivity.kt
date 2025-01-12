package com.sparshchadha.stocktracker

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.FrameLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.createGraph
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.fragment
import com.google.gson.Gson
import com.sparshchadha.stocktracker.core.base.presentation.fragment.MainHostFragment
import com.sparshchadha.stocktracker.core.common.extensions.getPhoneThemeAdjustedBackgroundColor
import com.sparshchadha.stocktracker.core.common.extensions.isDarkThemeOn
import com.sparshchadha.stocktracker.feature.search.presentation.fragment.SearchFragment
import com.sparshchadha.stocktracker.feature.stocks.presentation.fragment.StockDetailsFragment
import com.sparshchadha.stocktracker.navigation.CashCloudNavGraph
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var parentNavController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        setWindowAndContainerAttributes()

        setupNavGraph()
    }

    private fun setupNavGraph() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.mainHostFragmentContainer) as NavHostFragment
        parentNavController = navHostFragment.navController

        parentNavController.graph = parentNavController.createGraph(
            startDestination = CashCloudNavGraph.MainHostScreen.route
        ) {
            fragment<StockDetailsFragment>(CashCloudNavGraph.StockDetailsScreen.route) {
                label = "Stock Details"
                argument(CashCloudNavGraph.StockDetailsScreen.SYMBOL_KEY) { type = NavType.StringType }
            }
            fragment<MainHostFragment>(CashCloudNavGraph.MainHostScreen.route) {
                label = "Main Host Fragment"
            }
            fragment<SearchFragment>(CashCloudNavGraph.SearchScreen.route) {
                label = "Search Fragment"
            }
        }
    }

    @SuppressLint("CutPasteId")
    private fun setWindowAndContainerAttributes() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.app_container)) { _, insets ->
            insets
        }

        WindowCompat.getInsetsController(
            window,
            findViewById(R.id.app_container)
        ).isAppearanceLightStatusBars = !isDarkThemeOn()

        findViewById<FrameLayout>(R.id.app_container).setBackgroundColor(this.getPhoneThemeAdjustedBackgroundColor())
    }

    private fun getStocks(): CharSequence {
        val json = this.assets.open("stocks.json").bufferedReader().use { it.readText() }
        val gson = Gson()
        val data: List<Map<String, String>> =
            gson.fromJson(json, List::class.java) as List<Map<String, String>>
        return data.toString()
    }
}