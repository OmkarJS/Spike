package org.example.project.data.model

import kotlinx.serialization.Serializable

@Serializable
data class TranscriptRequest(
    val youtubeUrl: String
)

@Serializable
data class TranscriptResponse(
    val transcript: List<TranscriptItem>? = null,
    val error: String? = null
)

@Serializable
data class TranscriptItem(
    val text: String,
    val start: Double,
    val duration: Double
)
