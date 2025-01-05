package com.sparshchadha.stocktracker.feature.stocks.presentation.fragment

import android.os.Bundle
import android.view.View
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import com.sparshchadha.stocktracker.R
import com.sparshchadha.stocktracker.core.base.presentation.fragment.BaseFragment
import com.sparshchadha.stocktracker.core.theme.primaryAppBackground
import com.sparshchadha.stocktracker.core.theme.primaryTextColor
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StockDetailsFragment: BaseFragment(R.layout.fragment_stock_details) {
    private lateinit var cvStockDetailsFragment: ComposeView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cvStockDetailsFragment.setContent {
            Column (
                modifier = Modifier.fillMaxSize()
                    .background(primaryAppBackground)
            ) {
                Text("Stock details", color = primaryTextColor)
            }
        }
    }

    override fun initialiseViewsUsingView(view: View) {
        cvStockDetailsFragment = view.findViewById(R.id.cv_stock_details_fragment)
    }
}