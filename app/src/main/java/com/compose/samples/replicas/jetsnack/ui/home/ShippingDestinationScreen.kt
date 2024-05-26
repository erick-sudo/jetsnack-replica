package com.compose.samples.replicas.jetsnack.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.compose.samples.replicas.jetsnack.R
import com.compose.samples.replicas.jetsnack.model.ShippingDestination
import com.compose.samples.replicas.jetsnack.model.ShippingRepo
import com.compose.samples.replicas.jetsnack.model.rememberJetsnackSearchState
import com.compose.samples.replicas.jetsnack.ui.components.JetsnackDivider
import com.compose.samples.replicas.jetsnack.ui.components.JetsnackScaffold
import com.compose.samples.replicas.jetsnack.ui.components.JetsnackSurface
import com.compose.samples.replicas.jetsnack.ui.home.search.SearchBar
import com.compose.samples.replicas.jetsnack.ui.theme.JetsnackTheme

@Composable
fun ShippingDestinationScreen(
    onDismiss: () -> Unit,
    onShippingDestination: (ShippingDestination) -> Unit
) {

    val state = rememberJetsnackSearchState<ShippingDestination>()

    LaunchedEffect(state.query.text) {
        state.searching = true
        state.searchResults = ShippingRepo.searchShippingDestination(state.query.text)
        state.searching = false
    }

    Dialog(onDismissRequest = onDismiss) {
        JetsnackScaffold(
            topBar = {
                TopAppBar(
                    navigationIcon = {
                        IconButton(onClick = onDismiss) {
                            Icon(
                                imageVector = Icons.Filled.Close,
                                contentDescription = stringResource(id = R.string.close)
                            )
                        }
                    },
                    title = {

                    },
                    actions = {
                        SearchBar(
                            query = state.query,
                            onQueryChange = { state.query = it },
                            searchFocused = state.focused,
                            onSearchFocusChange = { state.focused = it },
                            onClearQuery = { state.query = TextFieldValue("") },
                            searching = state.searching
                        )
                    },
                    backgroundColor = JetsnackTheme.colors.uiBackground
                )
            }
        ) { paddingValues ->
            LazyColumn(
                modifier = Modifier
                    .padding(vertical = 8.dp),
                contentPadding = paddingValues,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(state.searchResults) { shippingDestination ->
                    ShippingDestinationItem(
                        shippingDestination = shippingDestination,
                        onShippingDestination = onShippingDestination,
                        clickable = true
                    )

                    JetsnackDivider()
                }
            }
        }
    }
}

@Composable
fun ShippingDestinationItem(
    shippingDestination: ShippingDestination,
    onShippingDestination: (ShippingDestination) -> Unit,
    modifier: Modifier = Modifier,
    clickable: Boolean = false,
) {
    JetsnackSurface(
        modifier = modifier
            .padding(horizontal = 8.dp)
            .fillMaxWidth()
            .clickable(enabled = clickable, onClick = { onShippingDestination(shippingDestination) }),
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
        ) {
            Row {
                Icon(imageVector = ImageVector.vectorResource(id = R.drawable.baseline_location_pin_24), contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    style = MaterialTheme.typography.h6,
                    text = shippingDestination.name
                )
            }
            Text(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                style = MaterialTheme.typography.body1,
                text = shippingDestination.address
            )
            Row(
                modifier = Modifier.padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    color = JetsnackTheme.colors.textHelp,
                    style = MaterialTheme.typography.body2,
                    text = shippingDestination.country,
                )
                Spacer(modifier = Modifier.width(5.dp))
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.baseline_arrow_forward_ios_24),
                    tint = JetsnackTheme.colors.textHelp,
                    contentDescription = null,
                    modifier = Modifier.height(15.dp)
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    color = JetsnackTheme.colors.textHelp,
                    style = MaterialTheme.typography.body2,
                    text = shippingDestination.state
                )
                Spacer(modifier = Modifier.width(5.dp))
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.baseline_arrow_forward_ios_24),
                    tint = JetsnackTheme.colors.textHelp,
                    contentDescription = null,
                    modifier = Modifier.height(15.dp)
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    color = JetsnackTheme.colors.textHelp,
                    style = MaterialTheme.typography.body2,
                    text = shippingDestination.city
                )
            }
            Row(
                modifier = Modifier.padding(horizontal = 16.dp),
            ) {
                Text(
                    text = "ZIP CODE:"
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = shippingDestination.zipCode
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun ShippingDestinationItemPreview() {
    ShippingDestinationItem(
        shippingDestination = ShippingRepo.defaultShippingDestination(),
        onShippingDestination = {  }
    )
}