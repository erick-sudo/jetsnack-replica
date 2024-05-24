package com.compose.samples.replicas.jetsnack.ui.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.add
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.compose.samples.replicas.jetsnack.model.Filter
import com.compose.samples.replicas.jetsnack.model.SnackCollection
import com.compose.samples.replicas.jetsnack.model.SnackRepo
import com.compose.samples.replicas.jetsnack.ui.components.FilterBar
import com.compose.samples.replicas.jetsnack.ui.components.JetsnackDivider
import com.compose.samples.replicas.jetsnack.ui.components.JetsnackScaffold
import com.compose.samples.replicas.jetsnack.ui.components.JetsnackSurface
import com.compose.samples.replicas.jetsnack.ui.components.SnackCollection
import com.compose.samples.replicas.jetsnack.ui.utils.OnNavigateToRoute
import com.compose.samples.replicas.jetsnack.ui.utils.OnSnackClick

@Composable
fun Feed(
    onSnackClick: OnSnackClick,
    onNavigateToRoute: OnNavigateToRoute,
    modifier: Modifier = Modifier
) {

    val snackCollections = remember {
        SnackRepo.getSnacks()
    }

    val filters = remember {
        SnackRepo.getFilters()
    }


    JetsnackScaffold(
        bottomBar = {
            JetsnackBottomBar(
                tabs = HomeSections.entries.toTypedArray(),
                currentRoute = HomeSections.FEED.route,
                navigateToRoute = onNavigateToRoute
            )
        },
        modifier = modifier
    ) { paddingValues ->
        Feed(snackCollections,
            filters,
            onSnackClick,
            Modifier.padding(paddingValues)
        )
    }
}

@Composable
private fun Feed(
    snackCollections: List<SnackCollection>,
    filters: List<Filter>,
    onSnackClick: OnSnackClick,
    modifier: Modifier = Modifier
) {
    JetsnackSurface(
        modifier = modifier
            .fillMaxSize()
    ) {
        Box {
            SnackCollectionList(
                snackCollections = snackCollections,
                filters = filters,
                onSnackClick = onSnackClick
            )
        }
    }
}

@Composable
private fun SnackCollectionList(
    snackCollections: List<SnackCollection>,
    filters: List<Filter>,
    onSnackClick: OnSnackClick,
    modifier: Modifier = Modifier
) {
    var filtersVisible by rememberSaveable {
        mutableStateOf(false)
    }

    Box(modifier) {
        LazyColumn {
            item {
                Spacer(
                    Modifier.windowInsetsTopHeight(
                        WindowInsets.statusBars.add(WindowInsets(top = 56.dp))
                    )
                )
                FilterBar(filters, onShowFilters = { filtersVisible = true })
            }

            itemsIndexed(snackCollections) { index, snackCollection ->
                if(index > 0) {
                    JetsnackDivider(thickness = 1.dp)
                }

                SnackCollection(
                    snackCollection = snackCollection,
                    onSnackClick = onSnackClick,
                    index = index
                )
            }
        }
    }

    AnimatedVisibility(
        visible = filtersVisible,
        enter = slideInVertically() + expandVertically(
            expandFrom = Alignment.Top
        ) + fadeIn(initialAlpha = 0.3f),
        exit = slideOutVertically(animationSpec = spring(stiffness = Spring.StiffnessLow, dampingRatio = 0.3f)) + shrinkVertically() + fadeOut()
    ) {
        FilterScreen(
            onDismiss = { filtersVisible = false }
        )
    }
}