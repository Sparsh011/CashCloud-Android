package com.sparshchadha.stocktracker.feature.search.presentation.fragment

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewAnimationUtils
import android.view.ViewGroup
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.sparshchadha.stocktracker.R
import com.sparshchadha.stocktracker.core.base.presentation.fragment.BaseFragment
import com.sparshchadha.stocktracker.core.common.utils.CashCloudNavigationAnimationSpec
import com.sparshchadha.stocktracker.core.common.utils.UiState
import com.sparshchadha.stocktracker.feature.search.data.remote.dto.Quote
import com.sparshchadha.stocktracker.feature.search.data.remote.dto.SecuritySearchResponse
import com.sparshchadha.stocktracker.feature.search.domain.entity.SearchHistoryEntity
import com.sparshchadha.stocktracker.feature.search.presentation.compose.SearchScreen
import com.sparshchadha.stocktracker.feature.search.presentation.viewmodel.SearchViewModel
import com.sparshchadha.stocktracker.navigation.CashCloudNavGraph
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : BaseFragment(R.layout.fragment_search) {
    private lateinit var cvSearchFragment: ComposeView

    private val searchViewModel: SearchViewModel by viewModels()

    private var shouldShowShimmer = mutableStateOf(false)
    private var securitySearchList = mutableStateListOf<Quote>()
    private var searchHistory = mutableStateListOf<SearchHistoryEntity>()
    private var errorDuringSearch = mutableStateOf<String?>(null)
    private var showHistory = mutableStateOf(true)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_search, container, false)
        root.post {
            val animator = ViewAnimationUtils.createCircularReveal(
                root,
                root.right,
                root.top,
                0f,
                maxOf(root.width, root.height).toFloat()
            )
            animator.duration = 300
            animator.start()
        }
        root.setBackgroundColor(Color.TRANSPARENT)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cvSearchFragment.setContent {
            SearchScreen(
                shouldShowLoader = shouldShowShimmer.value,
                onSearch = {
                    if (it.isBlank()) {
                        showHistory.value = true
                    }
                    searchViewModel.updateSearchQueryAndSearchForSecurity(it)
                },
                securitiesList = securitySearchList,
                onItemClick = { stockSymbol ->
                    // navigate to stock details screen with Symbol. At stock details screen, fetch stock details using symbol
                    navigateToStockDetailScreen(stockSymbol = stockSymbol)
                },
                showHistory = showHistory.value,
                onDeleteSearchHistoryItem = {
                    searchViewModel.deleteSearchHistoryItem(it)
                },
                searchHistory = searchHistory,
                onUpdateSearchHistory = {
                    searchViewModel.insertInSearchHistory(it)
                }
            )
        }

        observeSecuritySearchResponse()
        observeSearchHistory()
    }

    private fun navigateToStockDetailScreen(stockSymbol: String) {
        val bundle = Bundle()
        bundle.putString(CashCloudNavGraph.StockDetailsScreen.SYMBOL_KEY, stockSymbol)
        addFragmentToBackStack(findNavController(), CashCloudNavGraph.StockDetailsScreen.createRoute(stockSymbol), navigationAnimationSpec = CashCloudNavigationAnimationSpec.PUSH_BACK_CURRENT_SLIDE_IN_DESTINATION, args = bundle)
    }

    private fun observeSecuritySearchResponse() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                searchViewModel.securitySearchResponse.collect { state ->
                    handleSecuritySearchState(state)
                }
            }
        }
    }

    private fun observeSearchHistory() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                searchViewModel.searchHistory.collect { state ->
                    searchHistory.clear()
                    searchHistory.addAll(state ?: emptyList())
                }
            }
        }
    }

    private fun handleSecuritySearchState(state: UiState<SecuritySearchResponse>?) {
        if (state !is UiState.Loading) {
            shouldShowShimmer.value = false
        }

        when (state) {
            UiState.Loading -> {
                shouldShowShimmer.value = true
                showHistory.value = false
            }
            UiState.Empty -> {
                errorDuringSearch.value = "Could not find Securities matching the provided search query."
                showHistory.value = false
            }
            is UiState.Error -> {
                showHistory.value = false
                errorDuringSearch.value = state.error
            }
            is UiState.LoadingWithData -> {

            }
            is UiState.Success -> {
                showHistory.value = false
                securitySearchList.clear()
                state.data.quotes.let { quotes ->
                    securitySearchList.addAll(quotes)
                }
            }
            null -> {
                // show history in case of null and empty search query
            }
        }
    }

    override fun initialiseViewsUsingView(view: View) {
        cvSearchFragment = view.findViewById(R.id.cv_search_fragment)
    }
}