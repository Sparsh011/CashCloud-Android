package com.sparshchadha.stocktracker.core.base.presentation.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.AnimBuilder
import androidx.navigation.NavController
import com.sparshchadha.stocktracker.R
import com.sparshchadha.stocktracker.core.common.utils.CashCloudNavigationAnimationSpec

/**
 * Abstract BaseFragment class that will be inherited by all [Fragment] screens to utilise the same [addFragmentToBackStack] function across all fragments without requiring an external dependency. All of the methods are `protected` so that they can only be accessed from the fragment that inherits [BaseFragment] and not from any other class that contains an instance of a class inheriting from [BaseFragment].
 * */
abstract class BaseFragment(layoutId: Int) : Fragment(layoutId) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialiseViewsUsingView(view)
    }

    protected fun addFragmentToBackStack(
        fragment: Fragment,
        tag: String,
        containerId: Int = R.id.nav_host_fragment
    ) {
        requireActivity().supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(
                R.anim.slide_in_right,  // Enter animation for new fragment
                R.anim.push_back,       // Exit animation for old fragment
                R.anim.rise_up,         // Enter animation for old fragment on pop
                R.anim.slide_out_right  // Exit animation for new fragment on pop
            )
            .replace(containerId, fragment, tag)
            .addToBackStack(tag)
            .commit()
    }

    protected fun addFragmentToBackStack(
        navController: NavController,
        route: String,
        navigationAnimationSpec: CashCloudNavigationAnimationSpec = CashCloudNavigationAnimationSpec.PUSH_BACK_CURRENT_SLIDE_IN_DESTINATION,
        args: Bundle? = null
    ) {
        navController.navigate(
            route = route,
            builder = {
                anim {
                    val animBuilder = getNavigationAnimationSpec(navigationAnimationSpec)
                    animBuilder?.let {
                        enter = animBuilder.enter
                        exit = animBuilder.exit
                        popEnter = animBuilder.popEnter
                        popExit = animBuilder.popExit
                    }
                }
                popUpTo(navController.graph.startDestinationId) { saveState = true }
                launchSingleTop = true
                restoreState = true
                arguments = args
            }
        )
    }

    private fun getNavigationAnimationSpec(animationSpec: CashCloudNavigationAnimationSpec): AnimBuilder? {
        when (animationSpec) {
            CashCloudNavigationAnimationSpec.PUSH_BACK_CURRENT_SLIDE_IN_DESTINATION -> {
                return AnimBuilder().apply {
                    enter = R.anim.slide_in_right
                    exit = R.anim.push_back
                    popEnter = R.anim.rise_up
                    popExit = R.anim.slide_out_right
                }
            }

            CashCloudNavigationAnimationSpec.EXPAND_FROM_TOP_RIGHT -> {
                return AnimBuilder()
                    .apply {
                        enter = R.anim.expand
                        exit = R.anim.push_back
                        popEnter = R.anim.rise_up
                        popExit = R.anim.shrink
                    }
            }

            CashCloudNavigationAnimationSpec.NONE -> {
                return null
            }
        }
    }

    protected fun removeFragment(fragment: Fragment) {
        requireActivity().supportFragmentManager
            .beginTransaction()
            .remove(fragment)
            .commit()
    }

    protected fun popBackStack() {
        requireActivity().supportFragmentManager.popBackStack()
    }

    protected fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    abstract fun initialiseViewsUsingView(view: View)
}