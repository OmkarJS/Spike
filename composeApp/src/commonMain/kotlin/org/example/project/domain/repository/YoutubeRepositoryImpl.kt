package org.example.project.domain.repository

import org.example.project.data.model.YoutubeSearchResponse
import org.example.project.data.remote.YoutubeClient
import org.example.project.data.remote.util.ApiResponseWrapper

class YoutubeRepositoryImpl(
    private val youtubeClient: YoutubeClient
) : IYoutubeRepository {
    override suspend fun searchVideos(
        query: String,
        maxResults: Int
    ): ApiResponseWrapper<YoutubeSearchResponse> {
        return youtubeClient.searchVideos(query, maxResults)
    }
}
