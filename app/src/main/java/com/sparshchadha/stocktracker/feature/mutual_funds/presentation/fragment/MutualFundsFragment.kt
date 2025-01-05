package com.sparshchadha.stocktracker.feature.mutual_funds.presentation.fragment

import android.os.Bundle
import android.view.View
import androidx.compose.ui.platform.ComposeView
import com.sparshchadha.stocktracker.R
import com.sparshchadha.stocktracker.core.base.presentation.fragment.BaseFragment
import com.sparshchadha.stocktracker.feature.mutual_funds.presentation.compose.MutualFundsScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MutualFundsFragment: BaseFragment(R.layout.fragment_mutual_funds) {
    private lateinit var cvMutualFundsFragment: ComposeView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cvMutualFundsFragment.setContent {
            MutualFundsScreen()
        }
    }

    override fun initialiseViewsUsingView(view: View) {
        this.cvMutualFundsFragment = view.findViewById(R.id.cv_mutual_funds_fragment)
    }
}