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
import com.sparshchadha.stocktracker.core.common.utils.TimeRange
import com.sparshchadha.stocktracker.core.common.utils.UiState
import com.sparshchadha.stocktracker.feature.stocks.data.remote.dto.StockChartResponse
import com.sparshchadha.stocktracker.feature.stocks.data.remote.dto.StockFundamentalsResponse
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
    private var selectedTimeRange = mutableStateOf(TimeRange.DAY_1)
    private val stockFundaments = mutableStateOf<UiState<StockFundamentalsResponse>?>(null)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hasFetchedStockDetailsOnFragmentCreation =
            savedInstanceState?.getBoolean("hasFetchedStockDetailsOnFragmentCreation") ?: false
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(
            "hasFetchedStockDetailsOnFragmentCreation",
            hasFetchedStockDetailsOnFragmentCreation
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val symbol = arguments?.getString(CashCloudNavGraph.StockDetailsScreen.SYMBOL_KEY) ?: ""
        val exchange = arguments?.getString(CashCloudNavGraph.StockDetailsScreen.EXCHANGE_KEY) ?: ""
        if (!hasFetchedStockDetailsOnFragmentCreation) {
            hasFetchedStockDetailsOnFragmentCreation = true
            stockViewModel.getStockDetails(symbol, exchange)
        }

        cvStockDetailsFragment.setContent {
            StockDetailScreen(
                stockDetails = stockDetails.value,
                identifier = arguments?.getString(CashCloudNavGraph.StockDetailsScreen.SYMBOL_KEY)
                    ?: "",
                onBackPress = {
                    popBackStack()
                },
                onTimeUnitChange = {
                    stockViewModel.updateSelectedTimeRange(it)
                    if (it == TimeRange.ALL) {
                        stockViewModel.getStockDetailsFromInterval(
                            identifier = symbol,
                            ipoOfferingTime = getIpoOfferingTimeFromStockDetails()
                        )
                    } else {
                        stockViewModel.getStockDetailsFromInterval(identifier = symbol)
                    }
                },
                selectedTimeRange = selectedTimeRange.value,
                stockFundamentals = stockFundaments.value,
                onRetry = {
                    stockViewModel.getStockDetails(symbol, exchange)
                }
            )
        }

        observeStock()
        observeTimeRangeSelected()
        observeStockFundamentals()
    }

    private fun getIpoOfferingTimeFromStockDetails(): Long? {
        return when (stockDetails.value) {
            is UiState.Success -> {
                (stockDetails.value as UiState.Success<StockChartResponse>).data.chart.result[0].meta.firstTradeDate.toLong()
            }

            else -> {
                null
            }
        }
    }

    private fun observeStock() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                stockViewModel.stockChartData.collectLatest {
                    stockDetails.value = it
                }
            }
        }
    }

    private fun observeStockFundamentals() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                stockViewModel.stockFundamentalsData.collectLatest {
                    stockFundaments.value = it
                }
            }
        }
    }

    private fun observeTimeRangeSelected() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                stockViewModel.selectedTimeRange.collectLatest {
                    selectedTimeRange.value = it
                }
            }
        }
    }

    override fun initialiseViewsUsingView(view: View) {
        cvStockDetailsFragment = view.findViewById(R.id.cv_stock_details_fragment)
    }
}