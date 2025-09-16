package org.example.project.presentation.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import co.touchlab.kermit.Logger
import org.example.project.app.components.percentOfScreenWidth
import org.example.project.app.widget.HomeRoofView
import org.example.project.app.widget.YoutubeVideoItem
import org.example.project.navigation.Screens
import org.example.project.navigation.toJson
import org.example.project.presentation.theme.LocalAppColors
import org.koin.compose.koinInject

@Composable
fun HomePage() {
    val navigator = LocalNavigator.currentOrThrow
    val colors = LocalAppColors.current
    val homeViewModel: HomeViewModel = koinInject()

    // Search
    var searchText by remember { mutableStateOf("") }
    var isSearching by remember { mutableStateOf(false) }
    val searchedVideoListUiState by homeViewModel.searchedVideoListUiState.collectAsState()

    fun searchVideos(searchQuery: String) {
        homeViewModel.searchVideos(
            searchQuery = searchQuery
        )
    }

    // Search suggestions
    val searchSuggestionUiState by homeViewModel.searchSuggestionUiState.collectAsState()

    fun hideSearchSuggestion() {
        searchText = ""
        isSearching = false
        homeViewModel.updateSearchSuggestion(emptyList())
    }

    Scaffold(
        topBar = {
            HomeRoofView(
                colors = colors,
                isSearching = isSearching,
                searchText = searchText,
                onTextChange = {
                    searchText = it
                    if(it.isNotEmpty()) homeViewModel.getSearchSuggestions(it)
                    else homeViewModel.updateSearchSuggestion(emptyList())
                },
                onSearchClick = { searchQuery ->
                    hideSearchSuggestion()
                    searchVideos(searchQuery)
                },
                onSearchBarClick = {
                    isSearching = true
                },
                onProfileClick = {
                    navigator.push(Screens.ProfilePage)
                },
                onCloseSearch = {
                    hideSearchSuggestion()
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            @Composable
            fun SearchSuggestedItem(suggestion: String) {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(5.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(percentOfScreenWidth(1))
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Back",
                        tint = colors.black
                    )

                    Text(
                        text = suggestion,
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .clickable {
                                hideSearchSuggestion()
                                searchVideos(suggestion)
                            }
                            .padding(5.dp)
                    )

                    Icon(
                        imageVector = Icons.Default.ArrowForward,
                        contentDescription = "Back",
                        tint = colors.black,
                        modifier = Modifier.clickable {
                            searchText = suggestion
                        }
                    )

                }
            }

            @Composable
            fun DisplaySearchSuggestions() {
                val searchSuggestionList = searchSuggestionUiState.searchSuggestionList
                if(searchSuggestionList.isNotEmpty()) {
                    Logger.withTag("Omi").d("searchSuggestion: searchSuggestionList not empty")

                    LazyColumn(modifier = Modifier.padding(8.dp)) {
                        items(searchSuggestionList, key = { it.hashCode() }) { suggestion ->
                            SearchSuggestedItem(suggestion)
                        }
                    }
                }
            }

            @Composable
            fun DisplayYoutubeVideos() {
                LazyColumn {
                    val searchedVideoList = searchedVideoListUiState.searchedVideoList
                    if(searchedVideoList.isNotEmpty()) {
                        items(searchedVideoList, key = { it.id.videoId ?: it.hashCode() }) { video ->
                            YoutubeVideoItem(
                                videoItem = video,
                                onClick = {
                                    val json = video.toJson()
                                    navigator.push(Screens.SummaryPage(json))
                                    hideSearchSuggestion()
                                }
                            )
                        }
                    }
                }
            }

            if(searchSuggestionUiState.isLoading) {
                Logger.withTag("Omi").d("searchSuggestion: isLoading")
                CircularProgressIndicator()
            } else if(isSearching && searchSuggestionUiState.isLoading.not()) {
                searchSuggestionUiState.error?.let {
                    Logger.withTag("Omi").d("searchSuggestion: error")
                    Text("Search suggestion error")
                } ?: DisplaySearchSuggestions()
            }


            if(searchedVideoListUiState.isLoading) {
                CircularProgressIndicator()
            } else {
                searchedVideoListUiState.error?.let {
                    Text("Search suggestion error")
                } ?: DisplayYoutubeVideos()
            }
        }
    }
}

