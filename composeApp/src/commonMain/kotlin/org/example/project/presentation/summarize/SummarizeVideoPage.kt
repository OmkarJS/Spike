package org.example.project.presentation.summarize

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.example.project.app.components.MediumSpacer
import org.example.project.app.components.SmallSpacer
import org.example.project.app.constants.Constants
import org.example.project.app.utils.buildUrl
import org.example.project.app.utils.cleanTranscript
import org.example.project.app.utils.getBestAvailableThumbnail
import org.example.project.app.widget.Medium3Text
import org.example.project.app.widget.ThumbnailView
import org.example.project.app.widget.TopBarWidget
import org.example.project.data.model.YoutubeVideoItem
import org.example.project.presentation.theme.LocalAppColors
import org.koin.compose.koinInject

@Composable
fun SummarizeVideoPage(
    youtubeVideoItem: YoutubeVideoItem
) {
    val navigator = LocalNavigator.currentOrThrow
    val colors = LocalAppColors.current
    val summarizeVideoViewModel: SummarizeVideoViewModel = koinInject()

    val bestThumbnailUrl = getBestAvailableThumbnail(youtubeVideoItem)

    // States
    val transcriptUiState by summarizeVideoViewModel.transcriptUiState.collectAsState()

    fun fetchTranscript(url: String) {
        summarizeVideoViewModel.fetchTranscript(url)
    }

    val youtubeVideoUrl = buildUrl(youtubeVideoItem.id.videoId)
    LaunchedEffect(Unit) {
        youtubeVideoUrl?.let {
            fetchTranscript(it)
        }
    }

    @Composable
    fun ColumnScope.SummarizeVideoBody() {
        bestThumbnailUrl?.let {
            ThumbnailView(it)
        }

        SmallSpacer()

        Medium3Text(
            text = youtubeVideoItem.snippet.title,
            maxLines = 2,
            modifier = Modifier.fillMaxWidth()
        )

        MediumSpacer()

        val structuredTranscript = transcriptUiState.transcript?.let {
            cleanTranscript(it)
        }

        structuredTranscript?.let {
            LazyColumn {
                items(it) { sentence ->
                    Text(
                        text = sentence,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                }
            }
        } ?:

        Text(
            text = "Transcript not available.",
            fontSize = 16.sp,
            color = colors.grey,
            modifier = Modifier.fillMaxWidth()
        )

    }

    Scaffold(
        topBar = {
            TopBarWidget(
                title = Constants.Screen.SUMMARY_PAGE,
                onBackClick = {
                    navigator.pop()
                }
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
            .padding(padding)
            .fillMaxSize()
        ) {
            if (transcriptUiState.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            } else {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    transcriptUiState.error?.let {
                        Text(
                            text = it,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.fillMaxWidth(),
                            color = colors.black
                        )
                    } ?: SummarizeVideoBody()
                }
            }

        }
    }
}
