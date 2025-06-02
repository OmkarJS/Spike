package org.example.project.app.constants

class Constants {

    object Screen {
        const val HOME_PAGE = "Home"
        const val PROFILE_PAGE = "Profile"
        const val SUMMARY_PAGE = "Summary"
    }

    object Spike {
        const val APP_NAME = "SPIKE"
    }

    object Spiker {
        const val SPIKER_END_POINT = "https://8f44-2409-40f2-2e-57b0-7c04-ba1a-b65f-311d.ngrok-free.app/"
        const val FETCH_TRANSCRIPT_END_POINT = SPIKER_END_POINT + "transcript"
    }

    object Youtube {
        const val BASE_YOUTUBE_URL = "https://www.googleapis.com/youtube/v3/"
        const val SEARCH_END_POINT = BASE_YOUTUBE_URL + "search"
        const val YOUTUBE_DATA_KEY = "AIzaSyBzN2pJNovKrpGMn-HHp6UA5VFUsdfLCxA"
    }
}