package org.example.project.domain.repository

import org.example.project.data.remote.util.ApiResponseWrapper

interface ISpikerRepository {
    suspend fun fetchTranscript(youtubeUrl: String): ApiResponseWrapper<String>
}