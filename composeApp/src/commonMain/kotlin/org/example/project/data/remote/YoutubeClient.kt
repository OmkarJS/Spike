package org.example.project.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import org.example.project.app.constants.Constants
import org.example.project.data.model.YoutubeSearchResponse
import org.example.project.data.remote.util.ApiResponseWrapper
import org.example.project.data.remote.util.handleApiCall

class YoutubeClient(
    private val apiKey: String,
    private val httpClient: HttpClient
) {
    object YoutubeQueryParams {
        const val PART = "snippet"
        const val TYPE = "video"
        const val DURATION = "medium"
    }

    suspend fun searchVideos(
        query: String,
        maxResults: Int
    ): ApiResponseWrapper<YoutubeSearchResponse> {
        require(query.isNotBlank()) { "Query must not be blank" }
        return handleApiCall {
            httpClient.get(Constants.Youtube.SEARCH_END_POINT) {
                parameter("part", YoutubeQueryParams.PART)
                parameter("q", query)
                parameter("maxResults", maxResults)
                parameter("type", YoutubeQueryParams.TYPE)
                parameter("key", apiKey)
                parameter("videoDuration", YoutubeQueryParams.DURATION)
            }.body()
        }
    }
}