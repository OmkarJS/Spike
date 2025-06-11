package org.example.project.domain.repository

import org.example.project.data.model.YoutubeSearchResponse
import org.example.project.data.remote.util.ApiResponseWrapper

interface YoutubeRepository {
    suspend fun searchVideos(query: String, maxResults: Int): ApiResponseWrapper<YoutubeSearchResponse>

    suspend fun getSearchSuggestions(query: String): ApiResponseWrapper<List<String>>
}