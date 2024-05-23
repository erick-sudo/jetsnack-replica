package com.compose.samples.replicas.jetsnack.ui.home.cart

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.compose.samples.replicas.jetsnack.ui.utils.OnNavigateToRoute
import com.compose.samples.replicas.jetsnack.ui.utils.OnSnackClick

@Composable
fun Cart(
    onSnackClick: OnSnackClick,
    onNavigateToRoute: OnNavigateToRoute,
    modifier: Modifier = Modifier
) {
    Text(text = "Cart")
}