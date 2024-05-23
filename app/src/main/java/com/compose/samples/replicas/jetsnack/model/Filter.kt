package com.compose.samples.replicas.jetsnack.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.compose.samples.replicas.jetsnack.R

@Stable
class Filter(
    val name: String,
    enabled: Boolean = false,
    val icon: @Composable () -> ImageVector? = { null }
) {
    val enabled = mutableStateOf(enabled)
}

val filters = listOf(
    Filter(name = "Organic"),
    Filter(name = "Gluten-free"),
    Filter(name = "Dairy-free"),
    Filter(name = "Sweet"),
    Filter(name = "Savory")
)
val priceFilters = listOf(
    Filter(name = "$"),
    Filter(name = "$$"),
    Filter(name = "$$$"),
    Filter(name = "$$$$")
)
val sortFilters = listOf(
    Filter(name = "Android's favorite (default)", icon = { ImageVector.vectorResource(id = R.drawable.baseline_android_24) }),
    Filter(name = "Rating", icon = { Icons.Filled.Star }),
    Filter(name = "Alphabetical", icon = { ImageVector.vectorResource(id = R.drawable.baseline_sort_by_alpha_24) })
)

val categoryFilters = listOf(
    Filter(name = "Chips & crackers"),
    Filter(name = "Fruit snacks"),
    Filter(name = "Desserts"),
    Filter(name = "Nuts")
)
val lifeStyleFilters = listOf(
    Filter(name = "Organic"),
    Filter(name = "Gluten-free"),
    Filter(name = "Dairy-free"),
    Filter(name = "Sweet"),
    Filter(name = "Savory")
)

var sortDefault = sortFilters[0].name