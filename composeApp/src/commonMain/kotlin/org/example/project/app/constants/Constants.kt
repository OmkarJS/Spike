package org.example.project.app.constants

class Constants {

    object Spike {
        const val APP_NAME = "SPIKE"
    }

    object Spiker {
        const val SPIKER_END_POINT = "https://8b54-2409-40f2-4e-afa8-984b-f6d2-e7ff-f294.ngrok-free.app/"
        const val FETCH_TRANSCRIPT_END_POINT = SPIKER_END_POINT + "transcript"
    }

    object Youtube {
        const val BASE_YOUTUBE_URL = "https://www.googleapis.com/youtube/v3/"
        const val SEARCH_END_POINT = BASE_YOUTUBE_URL + "search"
        const val YOUTUBE_DATA_KEY = "AIzaSyBzN2pJNovKrpGMn-HHp6UA5VFUsdfLCxA"
    }
}