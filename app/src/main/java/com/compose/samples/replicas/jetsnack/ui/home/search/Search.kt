package com.compose.samples.replicas.jetsnack.ui.home.search

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.compose.samples.replicas.jetsnack.ui.utils.OnNavigateToRoute
import com.compose.samples.replicas.jetsnack.ui.utils.OnSnackClick

@Composable
fun Search(
    onSnackClick: OnSnackClick,
    onNavigateToRoute: OnNavigateToRoute,
    modifier: Modifier = Modifier
) {
    Text(text = "Search")
}