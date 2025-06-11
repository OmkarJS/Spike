package org.example.project.data.repository

import org.example.project.data.model.YoutubeSearchResponse
import org.example.project.data.remote.YoutubeClient
import org.example.project.data.remote.util.ApiResponseWrapper
import org.example.project.domain.repository.YoutubeRepository

class YoutubeRepositoryImpl(
    private val youtubeClient: YoutubeClient
) : YoutubeRepository {
    override suspend fun searchVideos(
        query: String,
        maxResults: Int
    ): ApiResponseWrapper<YoutubeSearchResponse> {
        return youtubeClient.searchVideos(query, maxResults)
    }

    override suspend fun getSearchSuggestions(query: String): ApiResponseWrapper<List<String>> {
        return youtubeClient.getSearchSuggestions(query)
    }
}
