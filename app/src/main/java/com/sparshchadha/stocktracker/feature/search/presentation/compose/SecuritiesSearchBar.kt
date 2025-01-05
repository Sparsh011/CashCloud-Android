package com.sparshchadha.stocktracker.feature.search.presentation.compose

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.traversalIndex
import com.sparshchadha.stocktracker.core.theme.primaryTextColor
import com.sparshchadha.stocktracker.core.theme.searchBarBackgroundColor
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SecuritiesSearchBar(
    onSearch: (String) -> Unit
) {
    var searchQuery by rememberSaveable { mutableStateOf("") }

    SearchBar(
        inputField = {
            SearchBarDefaults.InputField(
                query = searchQuery,
                onQueryChange = {
                    searchQuery = it
                    onSearch(searchQuery) // Send the callback when text is updated. Debouncing will be handled by the viewModel
                },
                onSearch = {
                    onSearch(it)
                },
                placeholder = {
                    ShuffleText(shufflingTexts = arrayOf("Mutual Funds", "Stocks", "ETFs"))
                },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                trailingIcon = {
                    Icon(
                        Icons.Default.Clear,
                        contentDescription = null,
                        modifier = Modifier.clickable { searchQuery = "" })
                },
                onExpandedChange = { },
                expanded = false,
                colors = TextFieldDefaults.colors(
                    focusedTextColor = primaryTextColor
                ),
            )
        },
        modifier = Modifier
            .semantics { traversalIndex = 0f }
            .fillMaxWidth(),
        expanded = false,
        onExpandedChange = {
        },
        colors = SearchBarDefaults.colors(
            containerColor = searchBarBackgroundColor
        ),
    ) {

    }
}

@Composable
private fun ShuffleText(
    placeholder: String = "Search for ",
    shufflingTexts: Array<String>
) {
    var textToDisplay by rememberSaveable {
        mutableStateOf(shufflingTexts[0])
    }

    LaunchedEffect(key1 = Unit) {
        var index = 1
        while (true) {
            if (index == shufflingTexts.size) index = 0
            delay(1500)
            textToDisplay = shufflingTexts[index]
            index++
        }
    }

    Row {
        Text(placeholder)
        AnimatedContent(
            targetState = textToDisplay,
            transitionSpec = {
                (slideInVertically(initialOffsetY = { it }) + fadeIn()).togetherWith(
                    slideOutVertically(
                        targetOffsetY = { -it }) + fadeOut()
                )
            }, label = ""
        ) { targetText ->
            Text(
                text = targetText,
            )
        }
    }

}