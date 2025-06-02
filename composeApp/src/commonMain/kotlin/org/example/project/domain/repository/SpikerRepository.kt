package org.example.project.domain.repository

import org.example.project.data.model.TranscriptResponse
import org.example.project.data.remote.util.ApiResponseWrapper

interface SpikerRepository {
    suspend fun fetchTranscript(youtubeVideoID: String): ApiResponseWrapper<TranscriptResponse>
}