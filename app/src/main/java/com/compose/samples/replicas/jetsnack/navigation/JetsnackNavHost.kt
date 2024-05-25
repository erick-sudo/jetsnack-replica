package com.compose.samples.replicas.jetsnack.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.compose.samples.replicas.jetsnack.ui.home.HomeSections
import com.compose.samples.replicas.jetsnack.ui.home.addHomeGraph
import com.compose.samples.replicas.jetsnack.ui.home.cart.CartViewModel
import com.compose.samples.replicas.jetsnack.ui.snackdetail.SnackDetail

@Composable
fun JetsnackNavHost(
    viewModel: CartViewModel = viewModel(factory = CartViewModel.provideFactory())
) {

    val jetsnackNavController = rememberJetsnackNavController()

    NavHost(
        navController = jetsnackNavController.navController,
        startDestination = Routes.Home.route
    ) {
        navigation(
            route = Routes.Home.route,
            startDestination = HomeSections.CART.route
        ) {
            addHomeGraph(
                viewModel = viewModel,
                onSnackSelected = jetsnackNavController::navigateToSnackDetail,
                onNavigateToRoute = jetsnackNavController::navigateToBottomBarRoute,
                increaseItemCount = viewModel::increaseSnackCount,
                decreaseItemCount = viewModel::decreaseSnackCount,
                removeSnack = viewModel::removeSnack,
            )
        }

        composable(
            route = "${Routes.SnackDetail.route}/{${Routes.SNACK_ID_KEY}}",
            arguments = listOf(navArgument(Routes.SNACK_ID_KEY) { type = NavType.LongType })
        ) { backStackEntry ->
            val arguments = requireNotNull(backStackEntry.arguments)
            val snackId = arguments.getLong(Routes.SNACK_ID_KEY)

            SnackDetail(
                snackId = snackId,
                upPress = jetsnackNavController::upPress,
                onSnackClick = { jetsnackNavController.navigateToSnackDetail(it, backStackEntry) },
                addToCart = viewModel::addToCart,
            )
        }
    }
}