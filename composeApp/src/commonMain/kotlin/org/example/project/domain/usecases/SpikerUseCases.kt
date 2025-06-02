package org.example.project.domain.usecases

import org.example.project.data.model.YoutubeSearchResponse
import org.example.project.data.remote.util.ApiResponseWrapper
import org.example.project.domain.repository.YoutubeRepository

class SearchYoutubeVideosUseCase(
    private val youtubeRepository: YoutubeRepository
) {
    suspend operator fun invoke(query: String, maxResults: Int): ApiResponseWrapper<YoutubeSearchResponse> {
        return youtubeRepository.searchVideos(query, maxResults)
    }
}