package org.example.project.data.model

import kotlinx.serialization.Serializable

@Serializable
data class TranscriptRequest(
    val youtubeUrl: String
)