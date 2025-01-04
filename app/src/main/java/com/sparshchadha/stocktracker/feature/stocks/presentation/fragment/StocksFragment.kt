package com.sparshchadha.stocktracker.feature.stocks.presentation.fragment

import android.os.Bundle
import android.view.View
import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.sparshchadha.stocktracker.R
import com.sparshchadha.stocktracker.core.base.presentation.BaseFragment
import com.sparshchadha.stocktracker.navigation.CashCloudNavGraph

class StocksFragment: BaseFragment(R.layout.fragment_stocks) {
    private lateinit var parentActivityNavController: NavController
    private lateinit var cvStocksFragment: ComposeView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initialiseViewsWithView(view)
        initialiseNavController()

        cvStocksFragment.setContent {
            Text("Stock Screen", modifier = Modifier.clickable {
                addFragmentToBackStack(parentActivityNavController, CashCloudNavGraph.MainScreenRoutes.STOCK_DETAILS_SCREEN)
            })
        }
    }

    private fun initialiseNavController() {
        parentActivityNavController = requireActivity().findNavController(R.id.mainHostFragmentContainer)
    }

    private fun initialiseViewsWithView(view: View) {
        this.cvStocksFragment = view.findViewById(R.id.cv_stock_fragment)
    }
}