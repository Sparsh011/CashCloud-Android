package com.sparshchadha.stocktracker

import android.os.Bundle
import android.view.View
import androidx.compose.material3.Text
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment

class MutualFundsFragment: Fragment(R.layout.fragment_mutual_funds) {
    private lateinit var cvMutualFundsFragment: ComposeView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        initialiseViewsWithView(view)

        cvMutualFundsFragment.setContent {
            Text("Mutual funds Screen")
        }
    }

    private fun initialiseViewsWithView(view: View) {
        this.cvMutualFundsFragment = view.findViewById(R.id.cv_mutual_funds_fragment)
    }

}