package com.compose.samples.replicas.jetsnack.ui.home.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.compose.samples.replicas.jetsnack.model.Filter
import com.compose.samples.replicas.jetsnack.model.SearchCategoryCollection
import com.compose.samples.replicas.jetsnack.model.SearchSuggestionGroup
import com.compose.samples.replicas.jetsnack.model.Snack
import com.compose.samples.replicas.jetsnack.ui.components.JetsnackDivider
import com.compose.samples.replicas.jetsnack.ui.components.JetsnackScaffold
import com.compose.samples.replicas.jetsnack.ui.components.JetsnackSurface
import com.compose.samples.replicas.jetsnack.ui.home.HomeSections
import com.compose.samples.replicas.jetsnack.ui.home.JetsnackBottomBar
import com.compose.samples.replicas.jetsnack.ui.theme.JetsnackTheme
import com.compose.samples.replicas.jetsnack.ui.utils.OnNavigateToRoute
import com.compose.samples.replicas.jetsnack.ui.utils.OnSnackClick
import com.compose.samples.replicas.jetsnack.R
import com.compose.samples.replicas.jetsnack.model.SearchRepo
import com.compose.samples.replicas.jetsnack.model.SnackRepo

@Composable
fun Search(
    onSnackClick: OnSnackClick,
    onNavigateToRoute: OnNavigateToRoute,
    modifier: Modifier = Modifier,
    state: SearchState = rememberSearchState()
) {
    JetsnackScaffold(
        bottomBar = {
            JetsnackBottomBar(
                tabs = HomeSections.entries.toTypedArray(),
                currentRoute = HomeSections.SEARCH.route,
                navigateToRoute = onNavigateToRoute
            )
        },
        modifier = modifier
    ) { paddingValues ->
        JetsnackSurface(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Column {
                Spacer(modifier = Modifier.statusBarsPadding())

                SearchBar(
                    query = state.query,
                    onQueryChange = { state.query = it },
                    searchFocused = state.focused,
                    onSearchFocusChange = { state.focused = it },
                    onClearQuery = { state.query = TextFieldValue("") },
                    searching = state.searching
                )

                JetsnackDivider()

                LaunchedEffect(state.query.text) {
                    state.searching = true
                    state.searchResults = SearchRepo.search(state.query.text)
                    state.searching = false
                }

                when(state.searchDisplay) {
                    SearchDisplay.Categories -> SearchCategories(state.categories)
                    SearchDisplay.Suggestions -> SearchSuggestions(
                        suggestions = state.suggestions,
                        onSuggestionSelect = { suggestion ->
                            state.query = TextFieldValue(suggestion)
                        }
                    )
                    SearchDisplay.Results -> SearchResults(
                        state.searchResults,
                        state.filters,
                        onSnackClick
                    )
                    SearchDisplay.NoResults -> NoResults(state.query.text)
                }
            }
        }
    }
}

enum class SearchDisplay {
    Categories, Suggestions, Results, NoResults
}

@Composable
private fun rememberSearchState(
    query: TextFieldValue = TextFieldValue(""),
    focused: Boolean = false,
    searching: Boolean = false,
    categories: List<SearchCategoryCollection> = SearchRepo.getCategories(),
    suggestions: List<SearchSuggestionGroup> = SearchRepo.getSuggestions(),
    filters: List<Filter> = SnackRepo.getFilters(),
    searchResults: List<Snack> = emptyList()
): SearchState = remember {
    SearchState(
        query = query,
        focused = focused,
        searching = searching,
        categories = categories,
        suggestions = suggestions,
        filters = filters,
        searchResults = searchResults
    )
}

@Stable
class SearchState(
    query: TextFieldValue,
    focused: Boolean,
    searching: Boolean,
    categories: List<SearchCategoryCollection>,
    suggestions: List<SearchSuggestionGroup>,
    filters: List<Filter>,
    searchResults: List<Snack>
) {
    var query by mutableStateOf(query)
    var focused by mutableStateOf(focused)
    var searching by mutableStateOf(searching)
    var categories by mutableStateOf(categories)
    var suggestions by mutableStateOf(suggestions)
    var filters by mutableStateOf(filters)
    var searchResults by mutableStateOf(searchResults)
    val searchDisplay: SearchDisplay
        get() = when {
            !focused && query.text.isEmpty() -> SearchDisplay.Categories
            focused && query.text.isEmpty() -> SearchDisplay.Suggestions
            searchResults.isEmpty() -> SearchDisplay.NoResults
            else -> SearchDisplay.Results
        }
}

@Composable
fun SearchBar(
    query: TextFieldValue,
    onQueryChange: (TextFieldValue) -> Unit,
    searchFocused: Boolean,
    onSearchFocusChange: (Boolean) -> Unit,
    onClearQuery: () -> Unit,
    searching: Boolean,
    modifier: Modifier = Modifier
) {
    JetsnackSurface(
        color = JetsnackTheme.colors.uiFloated,
        contentColor = JetsnackTheme.colors.textSecondary,
        shape = MaterialTheme.shapes.small,
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(horizontal = 24.dp, vertical = 8.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            if(query.text.isEmpty()) {
                SearchHint()
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentHeight()
            ) {
                if(searchFocused) {
                    IconButton(
                        onClick = onClearQuery,
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                            contentDescription =  stringResource(id = R.string.label_back)
                        )
                    }
                }

                BasicTextField(
                    value = query,
                    onValueChange = onQueryChange,
                    modifier = Modifier
                        .weight(1f)
                        .onFocusChanged {
                            onSearchFocusChange(it.isFocused)
                        }
                )

                if(searching) {
                    CircularProgressIndicator(
                        color = JetsnackTheme.colors.iconPrimary,
                        modifier = Modifier
                            .padding(horizontal = 6.dp)
                            .size(36.dp)
                    )
                } else {
                    Spacer(modifier = Modifier.width(IconSize))
                }
            }
        }
    }
}

private val IconSize = 48.dp

@Composable
private fun SearchHint() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize()
    ) {
        Icon(
            imageVector = Icons.Outlined.Search,
            contentDescription = stringResource(id = R.string.label_search)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = stringResource(id = R.string.search_jetsnack),
            color = JetsnackTheme.colors.textHelp
        )
    }
}

@Preview(showSystemUi = true)
@Composable
private fun SearchBarPreview() {

    val (text, setText) = mutableStateOf(TextFieldValue("Jetnack"))

    JetsnackTheme {
        JetsnackSurface {
            SearchBar(
                query = text,
                onQueryChange = { setText(it) },
                searchFocused = true,
                onSearchFocusChange = { },
                onClearQuery = { setText(TextFieldValue("")) },
                searching = true
            )
        }
    }
}