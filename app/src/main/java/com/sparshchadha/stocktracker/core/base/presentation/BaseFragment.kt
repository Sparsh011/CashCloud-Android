package com.sparshchadha.stocktracker.core.base.presentation

import android.widget.Toast
import androidx.fragment.app.Fragment
import com.sparshchadha.stocktracker.R

/**
 * Abstract BaseFragment class that will be inherited by all [Fragment] screens to utilise the same [addFragmentToBackStack] function across all fragments without requiring an external dependency. All of the methods are `protected` so that they can only be accessed from the fragment that inherits [BaseFragment] and not from any other class that contains an instance of a class inheriting from [BaseFragment].
 * */
abstract class BaseFragment(layoutId: Int): Fragment(layoutId) {

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
}