package com.sparshchadha.stocktracker.core.base.presentation

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.createGraph
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.fragment
import com.sparshchadha.stocktracker.R
import com.sparshchadha.stocktracker.feature.mutual_funds.presentation.fragment.MutualFundsFragment
import com.sparshchadha.stocktracker.feature.stocks.presentation.fragment.StocksFragment
import com.sparshchadha.stocktracker.navigation.CashCloudNavGraph

class MainHostFragment: BaseFragment(R.layout.fragment_main_host) {
    private lateinit var navController: NavController
    private lateinit var bbItemStocks: TextView
    private lateinit var bbItemMutualFunds: TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initialiseViewsUsingView(view)
        initializeNavController()
        setOnClickListeners()
    }

    private fun initialiseViewsUsingView(view: View) {
        bbItemStocks = view.findViewById(R.id.bb_item_stocks)
        bbItemMutualFunds = view.findViewById(R.id.bb_item_mutual_funds)
    }

    private fun initializeNavController() {
        val childNavHostFragment =
            childFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = childNavHostFragment.navController
        navController.graph = createNavGraph(navController)
    }


    private fun createNavGraph(navController: NavController): NavGraph {
        return navController.createGraph(
            startDestination = CashCloudNavGraph.BottomBarScreenRoutes.STOCKS_SCREEN
        ) {
            fragment<StocksFragment>(CashCloudNavGraph.BottomBarScreenRoutes.STOCKS_SCREEN)

            fragment<MutualFundsFragment>(CashCloudNavGraph.BottomBarScreenRoutes.MUTUAL_FUNDS_SCREEN)
        }
    }

    /**
     * Navigate to the given destination if it's not the current destination
     */
    private fun navigateIfNotCurrent(destinationRoute: String) {
        val currentDestinationRoute = navController.currentDestination?.route
        if (currentDestinationRoute != destinationRoute) {
            navController.navigate(destinationRoute) {
                popUpTo(navController.graph.startDestinationId) { saveState = true }
                launchSingleTop = true
                restoreState = true
            }
        }
    }

    private fun setOnClickListeners() {
        bbItemStocks.setOnClickListener {
            navigateIfNotCurrent(CashCloudNavGraph.BottomBarScreenRoutes.STOCKS_SCREEN)
        }

        bbItemMutualFunds.setOnClickListener {
            navigateIfNotCurrent(CashCloudNavGraph.BottomBarScreenRoutes.MUTUAL_FUNDS_SCREEN)
        }
    }
}