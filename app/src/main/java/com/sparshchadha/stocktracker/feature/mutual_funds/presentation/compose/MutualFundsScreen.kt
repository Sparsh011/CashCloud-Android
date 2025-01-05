package com.sparshchadha.stocktracker.feature.mutual_funds.presentation.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.traversalIndex
import com.sparshchadha.stocktracker.core.theme.primaryAppBackground
import com.sparshchadha.stocktracker.core.theme.primaryTextColor
import kotlin.math.exp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MutualFundsScreen(
    modifier: Modifier = Modifier,
) {
    Text("Mutual funds screen")
}