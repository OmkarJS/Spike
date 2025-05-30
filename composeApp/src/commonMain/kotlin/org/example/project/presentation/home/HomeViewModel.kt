package org.example.project.presentation.home

import co.touchlab.kermit.Logger
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.example.project.data.model.YoutubeVideoItem
import org.example.project.data.remote.util.ApiResponseWrapper
import org.example.project.domain.usecases.FetchTranscriptUseCase
import org.example.project.domain.usecases.SearchYoutubeVideosUseCase
import org.example.project.presentation.expectuals.getViewModelScope

class HomeViewModel(
    private val searchYoutubeVideosUseCase: SearchYoutubeVideosUseCase
) {
    private val viewModelScope: CoroutineScope = getViewModelScope()

    private var _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private var _isSearching = MutableStateFlow(false)
    val isSearching: StateFlow<Boolean> = _isSearching

    private var _youtubeVideoList = MutableStateFlow<List<YoutubeVideoItem>>(emptyList())
    var youtubeVideoList: StateFlow<List<YoutubeVideoItem>> = _youtubeVideoList

    // State to track errors
    private var _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun searchVideos(
        searchQuery: String,
        maxResults: Int = 10
    ) {
        _isSearching.value = true
        _error.value = null

        viewModelScope.launch {
            when(val result = searchYoutubeVideosUseCase.invoke(searchQuery, maxResults)) {
                is ApiResponseWrapper.Success -> {
                    Logger.withTag("Omi").d("ApiResponseWrapper Success")
                    _youtubeVideoList.value = result.data.videoItems
                    _isSearching.value = false
                }

                is ApiResponseWrapper.Failure -> {
                    Logger.withTag("Omi").d("ApiResponseWrapper Failure")
                    _isSearching.value = false
                }

                is ApiResponseWrapper.NetworkError -> {
                    Logger.withTag("Omi").d("ApiResponseWrapper NetworkError")
                    _isSearching.value = false
                }

                is ApiResponseWrapper.UnknownError -> {
                    Logger.withTag("Omi").d("ApiResponseWrapper UnknownError, ${result.error}")
                    _isSearching.value = false
                }
            }
        }
    }

}
