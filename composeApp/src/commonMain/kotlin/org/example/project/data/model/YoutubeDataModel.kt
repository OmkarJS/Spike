package org.example.project.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class YoutubeSearchResponse(
    @SerialName("items")
    val videoItems: List<YoutubeVideoItem> = emptyList()
)

@Serializable
data class YoutubeVideoItem(
    val id: VideoId,
    val snippet: Snippet
)

@Serializable
data class VideoId(
    val videoId: String? = null
)

@Serializable
data class Snippet(
    val title: String,
    val description: String,
    val thumbnails: Thumbnails,
    val channelTitle: String,
    val publishedAt: String
)

@Serializable
data class Thumbnails(
    val default: ThumbnailInfo? = null,
    val medium: ThumbnailInfo? = null,
    val high: ThumbnailInfo? = null,
    val standard: ThumbnailInfo? = null,
    val maxRes: ThumbnailInfo? = null
)

@Serializable
data class ThumbnailInfo(
    val url: String,
    val width: Int? = null,
    val height: Int? = null
)