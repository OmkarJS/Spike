package org.example.project.app.utils

import co.touchlab.kermit.Logger
import kotlin.time.Duration

fun parseDurationToSeconds(isoDuration: String): Long {
    return try {
        Duration.parse(isoDuration).inWholeSeconds
    } catch (e: Exception) {
        println("Error parsing duration $isoDuration: ${e.message}")
        0L
    }
}

fun parseDurationToHumanReadable(isoDuration: String): String {
    Logger.withTag("Omi").d("duration: $isoDuration")
    return try {
        val duration = Duration.parse(isoDuration)
        val hours = duration.inWholeHours
        val minutes = duration.inWholeMinutes
        val seconds = duration.inWholeSeconds

        buildString {
            if (hours > 0) append("${hours}:")
            if (minutes > 0 || hours > 0) append("${minutes}:")
            append("$seconds")
        }.trim()
    } catch (e: Exception) {
        println("Error parsing duration $isoDuration: ${e.message}")
        "--"
    }
}

fun formatTranscriptTime(seconds: Double): String {
    val totalSeconds = seconds.toInt()
    val hours = totalSeconds / 3600
    val minutes = (totalSeconds % 3600) / 60
    val secs = totalSeconds % 60

    return if (hours > 0) {
        "${hours.toString().padStart(2, '0')}:" +
                "${minutes.toString().padStart(2, '0')}:" +
                secs.toString().padStart(2, '0')
    } else {
        "${minutes.toString().padStart(2, '0')}:" +
                secs.toString().padStart(2, '0')
    }
}