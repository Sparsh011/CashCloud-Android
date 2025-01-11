package com.sparshchadha.stocktracker.feature.stocks.presentation.fragment

import android.os.Bundle
import android.view.View
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.sparshchadha.stocktracker.R
import com.sparshchadha.stocktracker.core.base.presentation.fragment.BaseFragment
import com.sparshchadha.stocktracker.core.common.utils.UiState
import com.sparshchadha.stocktracker.feature.stocks.data.remote.dto.StockChartResponse
import com.sparshchadha.stocktracker.feature.stocks.presentation.compose.stock_details.StockDetailScreen
import com.sparshchadha.stocktracker.feature.stocks.presentation.viewmodel.StockViewModel
import com.sparshchadha.stocktracker.navigation.CashCloudNavGraph
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class StockDetailsFragment : BaseFragment(R.layout.fragment_stock_details) {
    private lateinit var cvStockDetailsFragment: ComposeView

    private val stockViewModel: StockViewModel by viewModels()
    private var hasFetchedStockDetailsOnFragmentCreation = false

    private var stockDetails = mutableStateOf<UiState<StockChartResponse>?>(null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hasFetchedStockDetailsOnFragmentCreation = savedInstanceState?.getBoolean("hasFetchedStockDetailsOnFragmentCreation") ?: false
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean("hasFetchedStockDetailsOnFragmentCreation", hasFetchedStockDetailsOnFragmentCreation)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.getString(CashCloudNavGraph.StockDetailsScreen.SYMBOL_KEY)?.let {
            if (!hasFetchedStockDetailsOnFragmentCreation) {
                hasFetchedStockDetailsOnFragmentCreation = true
                stockViewModel.getStockChart(it)
            }
        }

        cvStockDetailsFragment.setContent {
            StockDetailScreen(
                stockDetails = stockDetails.value,
                identifier = arguments?.getString(CashCloudNavGraph.StockDetailsScreen.SYMBOL_KEY) ?: "",
                onBackPress = {
                    popBackStack()
                },
                onTimeUnitChange = {

                }
            )
        }

        observeStock()
    }

    private fun observeStock() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                stockViewModel.stockDetail.collectLatest {
                    stockDetails.value = it
                }
            }
        }
    }

    override fun initialiseViewsUsingView(view: View) {
        cvStockDetailsFragment = view.findViewById(R.id.cv_stock_details_fragment)
    }
}