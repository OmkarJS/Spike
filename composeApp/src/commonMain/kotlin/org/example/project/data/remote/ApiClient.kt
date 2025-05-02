package org.example.project.data.remote

import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.serialization.Serializable

class ApiClient {
    private val client = HttpClientEngine().create()

    private val baseUrl = "https://605b-2409-40f2-102b-e322-e048-a50e-6b2e-e294.ngrok-free.app/"

    suspend fun fetchTranscript(youtubeUrl: String): Result<String> {
        return try {
            val response: TranscriptResponse = client.post(baseUrl + "transcript") {
                contentType(ContentType.Application.Json)
                setBody(TranscriptRequest(youtubeUrl = youtubeUrl))
            }.body()

            if (response.error != null) {
                Result.failure(Exception(response.error))
            } else if (response.transcript != null) {
                Result.success(response.transcript)
            } else {
                Result.failure(Exception("Empty response received"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}


@Serializable
data class TranscriptRequest(
    val youtubeUrl: String
)

@Serializable
data class TranscriptResponse(
    val transcript: String? = null,
    val error: String? = null
)