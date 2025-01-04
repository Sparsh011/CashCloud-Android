package com.sparshchadha.stocktracker.feature.stocks.presentation.fragment

import android.os.Bundle
import android.view.View
import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.sparshchadha.stocktracker.R
import com.sparshchadha.stocktracker.core.base.presentation.BaseFragment

class StocksFragment: BaseFragment(R.layout.fragment_stocks) {
    private lateinit var cvStocksFragment: ComposeView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initialiseViewsWithView(view)

        cvStocksFragment.setContent {
            Text("Stock Screen", modifier = Modifier.clickable {
                addFragmentToBackStack(StockDetailsFragment(), "stockDetailsFragment")
            })
        }
    }

    private fun initialiseViewsWithView(view: View) {
        this.cvStocksFragment = view.findViewById(R.id.cv_stock_fragment)
    }
}