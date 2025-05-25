package org.example.project.domain.repository

import org.example.project.data.remote.SpikerClient
import org.example.project.data.remote.util.ApiResponseWrapper

class SpikerRepositoryImpl(
    private val ktorClient: SpikerClient
): ISpikerRepository {
    override suspend fun fetchTranscript(youtubeUrl: String): ApiResponseWrapper<String> {
        return ktorClient.fetchYoutubeTranscript(youtubeUrl)
    }
}