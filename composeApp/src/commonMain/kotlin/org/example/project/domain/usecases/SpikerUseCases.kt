package org.example.project.domain.usecases

import org.example.project.data.model.TranscriptResponse
import org.example.project.data.remote.util.ApiResponseWrapper
import org.example.project.domain.repository.SpikerRepository

class FetchTranscriptUseCase(
    private val transcriptRepository: SpikerRepository
) {
    suspend operator fun invoke(videoID: String): ApiResponseWrapper<TranscriptResponse> {
        return transcriptRepository.fetchTranscript(videoID)
    }
}