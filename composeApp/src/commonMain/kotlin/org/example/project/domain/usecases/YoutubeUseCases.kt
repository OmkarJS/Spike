package org.example.project.domain.usecases

import org.example.project.data.model.YoutubeSearchResponse
import org.example.project.data.remote.util.ApiResponseWrapper
import org.example.project.domain.repository.YoutubeRepository

class YoutubeUseCases (
    val searchYoutubeVideosUseCase: SearchYoutubeVideosUseCase,
    val getSearchSuggestions: GetSearchSuggestions
)

class SearchYoutubeVideosUseCase(
    private val youtubeRepository: YoutubeRepository
) {
    suspend operator fun invoke(query: String, maxResults: Int): ApiResponseWrapper<YoutubeSearchResponse> {
        return youtubeRepository.searchVideos(query, maxResults)
    }
}

class GetSearchSuggestions(
    private val youtubeRepository: YoutubeRepository
) {
    suspend operator fun invoke(query: String): ApiResponseWrapper<List<String>> {
         return youtubeRepository.getSearchSuggestions(query)
    }
}