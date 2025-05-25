package org.example.project.presentation.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import org.example.project.app.widget.simpleEdittextField
import org.example.project.presentation.home.HomeViewModel
import org.koin.compose.koinInject

@Composable
fun ManualTranscriptPage() {
    val homeViewModel: HomeViewModel = koinInject()
    var isLoading by remember { mutableStateOf(false) }

    // Transcript
    var youtubeUrl by remember { mutableStateOf("") }
    val transcriptReceived by homeViewModel.transcriptCollected.collectAsState()
    val transcriptError by homeViewModel.error.collectAsState()

    fun fetchTranscript(url: String) {
        homeViewModel.fetchTranscript(url)
    }

    fun onGetTranscriptClick() {
        if (youtubeUrl.isNotBlank()) {
            isLoading = true
            fetchTranscript(youtubeUrl)
            isLoading = false
        }
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .statusBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        transcriptError?.let {
            Text(
                text = it,
                color = MaterialTheme.colors.error,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }

        Text(
            text = transcriptReceived,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        )

        if (isLoading) {
            CircularProgressIndicator()
        }

        simpleEdittextField(
            value = youtubeUrl,
            onValueChange = {
                youtubeUrl = it
            },
            label = "YouTube URL",
            placeholder = "https://www.youtube.com/watch?v=...",
            keyboardType = KeyboardType.Uri
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                onGetTranscriptClick()
            },
            enabled = !isLoading && youtubeUrl.isNotBlank()
        ) {
            Text("Get Transcript")
        }
    }
}