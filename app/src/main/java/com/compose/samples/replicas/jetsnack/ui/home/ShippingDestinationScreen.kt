package com.compose.samples.replicas.jetsnack.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.window.Dialog
import com.compose.samples.replicas.jetsnack.model.ShippingDestination
import com.compose.samples.replicas.jetsnack.model.rememberJetsnackSearchState
import com.compose.samples.replicas.jetsnack.ui.components.JetsnackScaffold
import com.compose.samples.replicas.jetsnack.ui.components.JetsnackSurface
import com.compose.samples.replicas.jetsnack.ui.home.search.SearchBar

@Composable
fun ShippingDestinationScreen(
    onDismiss: () -> Unit,
    onShippingDestination: (ShippingDestination) -> Unit
) {

    val state = rememberJetsnackSearchState<ShippingDestination>()

    Dialog(onDismissRequest = onDismiss) {
        JetsnackScaffold(
            topBar = {
                TopAppBar {
                    SearchBar(
                        query = state.query,
                        onQueryChange = { state.query = it },
                        searchFocused = state.focused,
                        onSearchFocusChange = { state.focused = it },
                        onClearQuery = { state.query = TextFieldValue("") },
                        searching = state.searching
                    )
                }
            }
        ) {
            Column(

            ) {

            }
        }
    }
}

@Composable
fun ShippingDestinationItem(
    shippingDestination: ShippingDestination,
    onShippingDestination: (ShippingDestination) -> Unit
) {
    JetsnackSurface(
        modifier = Modifier
    ) {
        Text(text = "Select Shipping Address")
    }
}