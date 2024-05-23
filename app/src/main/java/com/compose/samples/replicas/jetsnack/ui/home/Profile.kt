package com.compose.samples.replicas.jetsnack.ui.home

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.compose.samples.replicas.jetsnack.ui.utils.OnNavigateToRoute

@Composable
fun Profile(
    onNavigateToRoute: OnNavigateToRoute,
    modifier: Modifier = Modifier
) {
    Text(text = "Profile")
}