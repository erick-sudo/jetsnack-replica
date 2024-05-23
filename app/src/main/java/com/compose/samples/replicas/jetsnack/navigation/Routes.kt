package com.compose.samples.replicas.jetsnack.navigation

sealed class Routes(
    val route: String
) {
    data object Home: Routes("home")
    data object SnackDetail: Routes("snack")
    companion object {
        const val SNACK_ID_KEY = "snackId"
    }
}