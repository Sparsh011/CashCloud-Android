package com.sparshchadha.stocktracker.feature.search.presentation.fragment

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewAnimationUtils
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import com.sparshchadha.stocktracker.R
import com.sparshchadha.stocktracker.core.base.presentation.fragment.BaseFragment
import com.sparshchadha.stocktracker.feature.search.presentation.compose.SearchScreen

class SearchFragment: BaseFragment(R.layout.fragment_search) {
    private lateinit var cvSearchFragment: ComposeView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cvSearchFragment.setContent {
            SearchScreen()
        }
    }

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


    override fun initialiseViewsUsingView(view: View) {
        cvSearchFragment = view.findViewById(R.id.cv_search_fragment)
    }
}