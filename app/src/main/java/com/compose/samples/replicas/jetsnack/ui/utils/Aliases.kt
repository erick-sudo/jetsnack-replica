package com.compose.samples.replicas.jetsnack.ui.utils

import androidx.navigation.NavBackStackEntry

typealias OnSnackSelected = (Long, NavBackStackEntry) -> Unit
typealias OnNavigateToRoute = (String) -> Unit
typealias OnSnackClick = (Long) -> Unit