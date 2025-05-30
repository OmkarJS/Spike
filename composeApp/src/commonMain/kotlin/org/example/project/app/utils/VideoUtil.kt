package org.example.project.app.utils

import org.example.project.data.model.YoutubeVideoItem

fun buildUrl(videoID: String?): String? {
    if(videoID == null) return null
    return "https://www.youtube.com/watch?v=$videoID"
}

fun cleanTranscript(raw: String): List<String> {
    val unescaped = raw
        .replace("\\\\", "\\")
        .replace("\\r", "\r")
        .replace("\\n", "\n")
        .replace("\"", "")

    return unescaped
        .replace(Regex("[\\r\\n]+"), " ")
        .split(Regex("(?<=[.!?])\\s+"))
        .map { it.trim() }
        .filter { line ->
            line.isNotBlank() &&
                    !line.contains("[Music]", ignoreCase = true) &&
                    !line.contains("[Applause]", ignoreCase = true) &&
                    !line.contains("foreign", ignoreCase = true)
        }
}

fun getBestAvailableThumbnail(youtubeVideoItem: YoutubeVideoItem): String? {
    val snippet = youtubeVideoItem.snippet
    return snippet.thumbnails.maxRes?.url
        ?: snippet.thumbnails.standard?.url
        ?: snippet.thumbnails.high?.url
        ?: snippet.thumbnails.medium?.url
        ?: snippet.thumbnails.default?.url
}