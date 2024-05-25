package com.compose.samples.replicas.jetsnack.model

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue

@Stable
class JetsnackSearchState<T>(
    query: TextFieldValue,
    focused: Boolean,
    searching: Boolean,
    searchResults: List<T>
) {
    var query by mutableStateOf(query)
    var focused by mutableStateOf(focused)
    var searching by mutableStateOf(searching)
    var searchResults by mutableStateOf(searchResults)
}

@Composable
fun <T> rememberJetsnackSearchState(
    query: TextFieldValue = TextFieldValue(""),
    focused: Boolean = false,
    searching: Boolean = false,
    searchResults: List<T> = emptyList()
): JetsnackSearchState<T> = remember {
    JetsnackSearchState(
        query = query,
        focused = focused,
        searching = searching,
        searchResults = searchResults
    )
}