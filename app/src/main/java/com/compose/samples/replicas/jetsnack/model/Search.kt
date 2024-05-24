package com.compose.samples.replicas.jetsnack.model

import androidx.compose.runtime.Immutable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

object SearchRepo {
    fun getCategories(): List<SearchCategoryCollection> = searchCategoryCollections
    fun getSuggestions(): List<SearchSuggestionGroup> = searchSuggestions

    suspend fun search(query: String): List<Snack> = withContext(Dispatchers.Default) {
        delay(200L) // Simulating an I/O delay
        snacks.filter{ it.name.contains(query, ignoreCase = true) }
    }
}

@Immutable
data class SearchCategoryCollection(
    val id: Long,
    val name: String,
    val categories: List<SearchCategory>
)

@Immutable
data class SearchCategory(
    val name: String,
    val imageUrl: String
)

@Immutable
data class SearchSuggestionGroup(
    val id: Long,
    val name: String,
    val suggestions: List<String>
)

/**
 * Static data
 */
private val searchCategoryCollections = listOf(
    SearchCategoryCollection(
        id = 0L,
        name = "Categories",
        categories = listOf(
            SearchCategory(
                name = "Chips & crackers",
                imageUrl = "$CDN_SERVER_URL/search/UsSdMZ78Q3E"
            ),
            SearchCategory(
                name = "Fruit snacks",
                imageUrl = "$CDN_SERVER_URL/search/SfP1PtM9Qa8"
            ),
            SearchCategory(
                name = "Desserts",
                imageUrl = "$CDN_SERVER_URL/search/_jk8KIyN_uA"
            ),
            SearchCategory(
                name = "Nuts ",
                imageUrl = "$CDN_SERVER_URL/search/UsSdMZ78Q3E"
            )
        )
    ),
    SearchCategoryCollection(
        id = 1L,
        name = "Lifestyles",
        categories = listOf(
            SearchCategory(
                name = "Organic",
                imageUrl = "$CDN_SERVER_URL/search/7meCnGCJ5Ms"
            ),
            SearchCategory(
                name = "Gluten Free",
                imageUrl = "$CDN_SERVER_URL/search/m741tj4Cz7M"
            ),
            SearchCategory(
                name = "Paleo",
                imageUrl = "$CDN_SERVER_URL/search/dt5-8tThZKg"
            ),
            SearchCategory(
                name = "Vegan",
                imageUrl = "$CDN_SERVER_URL/search/ReXxkS1m1H0"
            ),
            SearchCategory(
                name = "Vegetarian",
                imageUrl = "$CDN_SERVER_URL/search/IGfIGP5ONV0"
            ),
            SearchCategory(
                name = "Whole30",
                imageUrl = "$CDN_SERVER_URL/search/9MzCd76xLGk"
            )
        )
    )
)

private val searchSuggestions = listOf(
    SearchSuggestionGroup(
        id = 0L,
        name = "Recent searches",
        suggestions = listOf(
            "Cheese",
            "Apple Sauce"
        )
    ),
    SearchSuggestionGroup(
        id = 1L,
        name = "Popular searches",
        suggestions = listOf(
            "Organic",
            "Gluten Free",
            "Paleo",
            "Vegan",
            "Vegetarian",
            "Whole30"
        )
    )
)