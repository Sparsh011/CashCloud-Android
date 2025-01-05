package com.sparshchadha.stocktracker.feature.stocks.presentation.fragment

import android.os.Bundle
import android.view.View
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.sparshchadha.stocktracker.R
import com.sparshchadha.stocktracker.core.base.presentation.fragment.BaseFragment
import com.sparshchadha.stocktracker.core.theme.primaryAppBackground
import com.sparshchadha.stocktracker.core.theme.primaryTextColor
import com.sparshchadha.stocktracker.navigation.CashCloudNavGraph
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StocksFragment : BaseFragment(R.layout.fragment_stocks) {
    private lateinit var parentActivityNavController: NavController
    private lateinit var cvStocksFragment: ComposeView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initialiseNavController()

        cvStocksFragment.setContent {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(primaryAppBackground)
            ) {
                Text(
                    "Stock Screen",
                    color = primaryTextColor,
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable {
                            addFragmentToBackStack(
                                navController = parentActivityNavController,
                                route = CashCloudNavGraph.MainScreenRoutes.STOCK_DETAILS_SCREEN
                            )
                        })
            }
        }
    }

    private fun initialiseNavController() {
        parentActivityNavController =
            requireActivity().findNavController(R.id.mainHostFragmentContainer)
    }

    override fun initialiseViewsUsingView(view: View) {
        this.cvStocksFragment = view.findViewById(R.id.cv_stock_fragment)
    }
}