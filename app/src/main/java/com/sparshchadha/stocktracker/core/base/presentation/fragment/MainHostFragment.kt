package com.sparshchadha.stocktracker.core.base.presentation.fragment

import android.os.Bundle
import android.view.View
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.vectorResource
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.createGraph
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.fragment
import com.sparshchadha.stocktracker.R
import com.sparshchadha.stocktracker.core.base.presentation.compose.MainHostFragmentTopBar
import com.sparshchadha.stocktracker.core.common.utils.CashCloudNavigationAnimationSpec
import com.sparshchadha.stocktracker.core.theme.bottomBarBackgroundColor
import com.sparshchadha.stocktracker.core.theme.bottomBarSelectedIconColor
import com.sparshchadha.stocktracker.core.theme.bottomBarUnselectedIconColor
import com.sparshchadha.stocktracker.feature.mutual_funds.presentation.fragment.MutualFundsFragment
import com.sparshchadha.stocktracker.feature.stocks.presentation.fragment.StocksFragment
import com.sparshchadha.stocktracker.navigation.CashCloudNavGraph
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainHostFragment : BaseFragment(R.layout.fragment_main_host) {
    private lateinit var navController: NavController
    private lateinit var bbItemStocks: ComposeView
    private lateinit var bbItemMutualFunds: ComposeView
    private var selectedBottomBarItem =
        mutableStateOf(CashCloudNavGraph.BottomBarScreenRoutes.STOCKS_SCREEN)
    private lateinit var cvTopBar: ComposeView

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("SELECTED_BOTTOM_BAR_ICON", selectedBottomBarItem.value)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        selectedBottomBarItem.value = savedInstanceState?.getString("SELECTED_BOTTOM_BAR_ICON")
            ?: CashCloudNavGraph.BottomBarScreenRoutes.STOCKS_SCREEN
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeNavController()
        setupTopBar()
        setupBottomBar()
    }

    private fun setupTopBar() {
        cvTopBar.setContent {
            MainHostFragmentTopBar(onSearchIconClick = {
                addFragmentToBackStack(findNavController(), CashCloudNavGraph.MainScreenRoutes.SEARCH_SCREEN, CashCloudNavigationAnimationSpec.EXPAND_FROM_TOUCH_POINT)
            })
        }
    }

    override fun initialiseViewsUsingView(view: View) {
        bbItemStocks = view.findViewById(R.id.bb_item_stocks)
        bbItemMutualFunds = view.findViewById(R.id.bb_item_mutual_funds)
        cvTopBar = view.findViewById(R.id.cv_main_host_top_bar)
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

    private fun setupBottomBar() {
        bbItemStocks.setContent {
            Column(
                modifier = Modifier
                    .background(bottomBarBackgroundColor)
                    .fillMaxWidth()
                    .clickable {
                        selectedBottomBarItem.value =
                            CashCloudNavGraph.BottomBarScreenRoutes.STOCKS_SCREEN
                        navigateIfNotCurrent(CashCloudNavGraph.BottomBarScreenRoutes.STOCKS_SCREEN)
                    },
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_stocks),
                    contentDescription = null,
                    tint = if (selectedBottomBarItem.value == CashCloudNavGraph.BottomBarScreenRoutes.STOCKS_SCREEN) bottomBarSelectedIconColor else bottomBarUnselectedIconColor
                )

                Text(
                    text = "Stocks",
                    color = if (selectedBottomBarItem.value == CashCloudNavGraph.BottomBarScreenRoutes.STOCKS_SCREEN) bottomBarSelectedIconColor else bottomBarUnselectedIconColor
                )
            }
        }

        bbItemMutualFunds.setContent {
            Column(
                modifier = Modifier
                    .background(bottomBarBackgroundColor)
                    .fillMaxWidth()
                    .clickable {
                        selectedBottomBarItem.value =
                            CashCloudNavGraph.BottomBarScreenRoutes.MUTUAL_FUNDS_SCREEN
                        navigateIfNotCurrent(CashCloudNavGraph.BottomBarScreenRoutes.MUTUAL_FUNDS_SCREEN)
                    },
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_mutual_fund),
                    contentDescription = null,
                    tint = if (selectedBottomBarItem.value == CashCloudNavGraph.BottomBarScreenRoutes.MUTUAL_FUNDS_SCREEN) bottomBarSelectedIconColor else bottomBarUnselectedIconColor
                )

                Text(
                    text = "Mutual Funds",
                    color = if (selectedBottomBarItem.value == CashCloudNavGraph.BottomBarScreenRoutes.MUTUAL_FUNDS_SCREEN) bottomBarSelectedIconColor else bottomBarUnselectedIconColor
                )
            }
        }
    }
}