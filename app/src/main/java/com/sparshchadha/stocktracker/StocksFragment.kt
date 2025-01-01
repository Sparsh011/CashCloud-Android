package com.sparshchadha.stocktracker

import android.os.Bundle
import android.view.View
import androidx.compose.material3.Text
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment

class StocksFragment: Fragment(R.layout.fragment_stocks) {
    private lateinit var cvStocksFragment: ComposeView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initialiseViewsWithView(view)

        cvStocksFragment.setContent {
            Text("Stock Screen")
        }
    }

    private fun initialiseViewsWithView(view: View) {
        this.cvStocksFragment = view.findViewById(R.id.cv_stock_fragment)
    }
}