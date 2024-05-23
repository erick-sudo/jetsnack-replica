package com.compose.samples.replicas.jetsnack.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.compose.samples.replicas.jetsnack.ui.home.HomeSections
import com.compose.samples.replicas.jetsnack.ui.home.addHomeGraph
import com.compose.samples.replicas.jetsnack.ui.snackdetail.SnackDetail

@Composable
fun JetsnackNavHost() {

    val jetsnackNavController = rememberJetsnackNavController()

    NavHost(
        navController = jetsnackNavController.navController,
        startDestination = Routes.Home.route
    ) {
        navigation(
            route = Routes.Home.route,
            startDestination = HomeSections.FEED.route
        ) {
            addHomeGraph(
                onSnackSelected = jetsnackNavController::navigateToSnackDetail,
                onNavigateToRoute = jetsnackNavController::navigateToBottomBarRoute
            )
        }

        composable(
            route = "${Routes.SnackDetail.route}/${Routes.SNACK_ID_KEY}"
        ) { backStackEntry ->
            val arguments = requireNotNull(backStackEntry.arguments)
            val snackId = arguments.getLong(Routes.SNACK_ID_KEY)

            SnackDetail(snackId, jetsnackNavController::upPress)
        }
    }
}