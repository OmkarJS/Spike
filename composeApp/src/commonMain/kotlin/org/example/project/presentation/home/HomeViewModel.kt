package org.example.project.presentation.home

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.example.project.data.model.YoutubeVideoItem
import org.example.project.data.remote.util.parseApiResponse
import org.example.project.domain.usecases.YoutubeUseCases
import org.example.project.presentation.expectuals.getViewModelScope

class HomeViewModel(
    private val youtubeUseCases: YoutubeUseCases
) {
    private val viewModelScope: CoroutineScope = getViewModelScope()

    // Searched video list
    private val _searchedVideoListUiState = MutableStateFlow(SearchedVideoListUiState())
    val searchedVideoListUiState: StateFlow<SearchedVideoListUiState> = _searchedVideoListUiState.asStateFlow()

    // Search suggestion list
    private val _searchSuggestionUiState = MutableStateFlow(SearchSuggestionUiState())
    val searchSuggestionUiState: StateFlow<SearchSuggestionUiState> = _searchSuggestionUiState.asStateFlow()

    fun searchVideos(
        searchQuery: String,
        maxResults: Int = 50
    ) {
        _searchedVideoListUiState.update { it.copy(isLoading = true) }

        viewModelScope.launch {
            parseApiResponse(
                request = { youtubeUseCases.searchYoutubeVideosUseCase.invoke(searchQuery, maxResults) },
                onSuccess = {
                    _searchedVideoListUiState.update { searchedVideoListUiState ->
                        searchedVideoListUiState.copy(
                            searchedVideoList = it.videoItems,
                            isLoading = false
                        )
                    }
                },
                onError = {
                    _searchedVideoListUiState.update { searchedVideoListUiState ->
                        searchedVideoListUiState.copy(
                            isLoading = false,
                            error = it
                        )
                    }
                }
            )
        }
    }

    fun getSearchSuggestions(
        searchQuery: String
    ) {
        _searchSuggestionUiState.update { it.copy(isLoading = true) }

        viewModelScope.launch {
            parseApiResponse(
                request = { youtubeUseCases.getSearchSuggestions(searchQuery) },
                onSuccess = {
                    _searchSuggestionUiState.update { searchSuggestionUiState ->
                        searchSuggestionUiState.copy(
                            searchSuggestionList = it,
                            isLoading = false
                        )
                    }
                },
                onError = {
                    _searchSuggestionUiState.update { searchSuggestionUiState ->
                        searchSuggestionUiState.copy(
                            isLoading = false,
                            error = it
                        )
                    }
                }
            )
        }
    }

    fun updateSearchSuggestion(suggestions: List<String>) {
        _searchSuggestionUiState.update {
            it.copy(
                searchSuggestionList = suggestions
            )
        }
    }

}

data class SearchedVideoListUiState (
    val isLoading: Boolean = false,
    val searchedVideoList: List<YoutubeVideoItem> = emptyList(),
    val error: String? = null
)

data class SearchSuggestionUiState (
    val isLoading: Boolean = false,
    val searchSuggestionList: List<String> = emptyList(),
    val error: String? = null
)
