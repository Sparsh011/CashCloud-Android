package com.sparshchadha.stocktracker

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.createGraph
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.fragment
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {
    private lateinit var parentNavController: NavController
    private lateinit var bbItemStocks: TextView
    private lateinit var bbItemMutualFunds: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initialiseViews()
        setupNavGraph()
        setOnClickListeners()
    }

    private fun setOnClickListeners() {
        bbItemStocks.setOnClickListener {
            navigateIfNotCurrent(CashCloudNavGraph.BottomBarScreenRoutes.STOCKS_SCREEN)
        }

        bbItemMutualFunds.setOnClickListener {
            navigateIfNotCurrent(CashCloudNavGraph.BottomBarScreenRoutes.MUTUAL_FUNDS_SCREEN)
        }
    }

    private fun initialiseViews() {
        bbItemStocks = findViewById(R.id.bb_item_stocks)
        bbItemMutualFunds = findViewById(R.id.bb_item_mutual_funds)
    }

    private fun setupNavGraph() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        parentNavController = navHostFragment.navController

        parentNavController.graph = parentNavController.createGraph(
            startDestination = CashCloudNavGraph.BottomBarScreenRoutes.STOCKS_SCREEN
        ) {
            fragment<StocksFragment>(CashCloudNavGraph.BottomBarScreenRoutes.STOCKS_SCREEN)
            fragment<MutualFundsFragment>(CashCloudNavGraph.BottomBarScreenRoutes.MUTUAL_FUNDS_SCREEN)
        }
    }

    private fun getStocks(): CharSequence {
        val json = this.assets.open("stocks.json").bufferedReader().use { it.readText() }
        val gson = Gson()
        val data: List<Map<String, String>> = gson.fromJson(json, List::class.java) as List<Map<String, String>>
        return data.toString()
    }

    /**
     * Navigate to the given destination if it's not the current destination
     */
    private fun navigateIfNotCurrent(destinationRoute: String) {
        val currentDestinationRoute = parentNavController.currentDestination?.route
        if (currentDestinationRoute != destinationRoute) {
            parentNavController.navigate(destinationRoute) {
                popUpTo(parentNavController.graph.startDestinationId) { saveState = true }
                launchSingleTop = true
                restoreState = true
            }
        }
    }
}