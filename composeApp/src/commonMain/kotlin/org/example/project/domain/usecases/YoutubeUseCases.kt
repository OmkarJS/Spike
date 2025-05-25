package org.example.project.domain.usecases

import org.example.project.data.remote.util.ApiResponseWrapper
import org.example.project.domain.repository.ISpikerRepository

class FetchTranscriptUseCase(
    private val transcriptRepository: ISpikerRepository
) {
    suspend operator fun invoke(url: String): ApiResponseWrapper<String> {
        return transcriptRepository.fetchTranscript(url)
    }
}