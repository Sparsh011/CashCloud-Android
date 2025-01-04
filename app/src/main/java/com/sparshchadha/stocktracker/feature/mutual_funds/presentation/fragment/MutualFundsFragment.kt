package com.sparshchadha.stocktracker.feature.mutual_funds.presentation.fragment

import android.os.Bundle
import android.view.View
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.sparshchadha.stocktracker.R
import com.sparshchadha.stocktracker.core.theme.primaryAppBackground
import com.sparshchadha.stocktracker.core.theme.primaryTextColor

class MutualFundsFragment: Fragment(R.layout.fragment_mutual_funds) {
    private lateinit var cvMutualFundsFragment: ComposeView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        initialiseViewsWithView(view)

        cvMutualFundsFragment.setContent {
            Column (
                modifier = Modifier.fillMaxSize()
                    .background(primaryAppBackground)
            ) {
                Text(" Mutual funds screen", color = primaryTextColor)
            }
        }
    }

    private fun initialiseViewsWithView(view: View) {
        this.cvMutualFundsFragment = view.findViewById(R.id.cv_mutual_funds_fragment)
    }

}