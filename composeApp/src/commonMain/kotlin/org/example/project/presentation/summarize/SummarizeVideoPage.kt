package org.example.project.presentation.summarize

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.example.project.app.components.MediumSpacer
import org.example.project.app.components.SmallSpacer
import org.example.project.app.components.percentOfScreenHeight
import org.example.project.app.components.percentOfScreenWidth
import org.example.project.app.constants.Constants
import org.example.project.app.utils.formatTranscriptTime
import org.example.project.app.utils.getBestAvailableThumbnail
import org.example.project.app.widget.Medium1Text
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

    fun fetchTranscript(youtubeVideoID: String) {
        summarizeVideoViewModel.fetchTranscript(youtubeVideoID)
    }

    val youtubeVideoID = youtubeVideoItem.id.videoId
    LaunchedEffect(Unit) {
        youtubeVideoID?.let {
            fetchTranscript(it)
        }
    }

    @Composable
    fun ColumnScope.SummarizeVideoBody(
        onClickTimeStamp: (Double) -> Unit
    ) {
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

        transcriptUiState.transcriptList?.let {
            LazyColumn {
                items(it) { transcriptItem ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                    ) {
                        Medium1Text(
                            text = formatTranscriptTime(transcriptItem.start),
                            modifier = Modifier
                                .width(50.dp)
                                .clickable {
                                    onClickTimeStamp(transcriptItem.start)
                                },
                            textColor = Color.Cyan
                        )

                        Spacer(modifier = Modifier.width(10.dp))

                        Medium1Text(
                            text = transcriptItem.text,
                            maxLines = Int.MAX_VALUE,
                            overflow = TextOverflow.Clip,
                            modifier = Modifier
                                .weight(1f)
                        )
                    }
                }
            }
        }
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
            Column(
                modifier = Modifier
                    .padding(
                        start = percentOfScreenWidth(2),
                        end = percentOfScreenWidth(2),
                        bottom = percentOfScreenHeight(2)
                    )
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                when {
                    transcriptUiState.error != null -> {
                        transcriptUiState.error?.let {
                            Text(
                                text = it,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.fillMaxWidth(),
                                color = colors.black
                            )
                        }
                    }
                    else -> {
                        SummarizeVideoBody(
                            onClickTimeStamp = {}
                        )
                    }
                }
            }

            if (transcriptUiState.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}
