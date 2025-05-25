package org.example.project.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import org.example.project.app.constants.Constants
import org.example.project.data.model.TranscriptRequest
import org.example.project.data.remote.util.ApiResponseWrapper
import org.example.project.data.remote.util.handleApiCall

class SpikerClient(
    private val httpClient: HttpClient
) {
    suspend fun fetchYoutubeTranscript(youtubeUrl: String): ApiResponseWrapper<String> {
        return handleApiCall {
            httpClient.post(Constants.Spiker.FETCH_TRANSCRIPT_END_POINT) {
                contentType(ContentType.Application.Json)
                setBody(TranscriptRequest(youtubeUrl = youtubeUrl))
            }.body()
        }
    }
}