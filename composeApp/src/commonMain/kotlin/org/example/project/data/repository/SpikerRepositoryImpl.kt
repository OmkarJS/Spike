package org.example.project.data.repository

import org.example.project.data.model.TranscriptResponse
import org.example.project.data.remote.SpikerClient
import org.example.project.data.remote.util.ApiResponseWrapper
import org.example.project.domain.repository.SpikerRepository

class SpikerRepositoryImpl(
    private val ktorClient: SpikerClient
): SpikerRepository {
    override suspend fun fetchTranscript(youtubeVideoID: String): ApiResponseWrapper<TranscriptResponse> {
        return ktorClient.fetchYoutubeTranscript(youtubeVideoID)
    }
}