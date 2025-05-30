package org.example.project.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.example.project.app.widget.HomeRoofView
import org.example.project.app.widget.YoutubeVideoItem
import org.example.project.presentation.navigation.Screens
import org.example.project.presentation.navigation.toJson
import org.example.project.presentation.theme.LocalAppColors
import org.koin.compose.koinInject

@Composable
fun HomePage() {
    val navigator = LocalNavigator.currentOrThrow
    val colors = LocalAppColors.current

    val homeViewModel: HomeViewModel = koinInject()
    var isLoading by remember { mutableStateOf(false) }

    // Search
    val searchedVideoList by homeViewModel.youtubeVideoList.collectAsState()
    fun searchVideos(searchQuery: String) {
        homeViewModel.searchVideos(
            searchQuery = searchQuery
        )
    }

    Scaffold (
        topBar = {
            HomeRoofView(
                colors = colors,
                onSearchClick = { searchQuery ->
                    searchVideos(searchQuery)
                },
                onProfileClick = {
                    navigator.push(Screens.ProfilePage)
                }
            )
        }
    ) { paddingValues ->
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            if (isLoading) {
                CircularProgressIndicator()
            }

            if(searchedVideoList.isNotEmpty()) {
                LazyColumn {
                    items(searchedVideoList, key = { it.id.videoId ?: it.hashCode() }) { video ->
                        YoutubeVideoItem(
                            videoItem = video,
                            onClick = {
                                val json = video.toJson()
                                navigator.push(Screens.SummaryPage(json))
                            }
                        )
                    }
                }
            }

        }
    }
}

