package org.example.project.presentation.home

import androidx.compose.runtime.mutableStateOf
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.example.project.data.remote.ApiClient

class HomeViewModel(
    private val viewModelScope: CoroutineScope
) {
    private val apiClient = ApiClient()
    private val _transcriptCollected = MutableStateFlow("")
    val transcriptCollected: StateFlow<String> = _transcriptCollected.asStateFlow()

    private var _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    // State to track errors
    private var _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun fetchTranscript(youtubeUrl: String) {
        _isLoading.value = true
        _error.value = null

        viewModelScope.launch {
            apiClient.fetchTranscript(youtubeUrl).fold(
                onSuccess = { transcriptText ->
                    _transcriptCollected.value = transcriptText
                    _isLoading.value = false
                },
                onFailure = { exception ->
                    _error.value = exception.message ?: "Unknown error occurred"
                    _isLoading.value = false
                }
            )
        }
    }

}
