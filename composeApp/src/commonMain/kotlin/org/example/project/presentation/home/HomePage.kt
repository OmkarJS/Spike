package org.example.project.presentation.home

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
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.example.project.presentation.components.LargeSpacer
import org.example.project.presentation.expectuals.getViewModelScope
import org.example.project.presentation.navigation.Screens
import org.example.project.presentation.widgets.ProfileView
import org.example.project.presentation.widgets.simpleEdittextField

@Composable
fun HomePage() {
    val navigator = LocalNavigator.currentOrThrow
    val homeViewModel = HomeViewModel(getViewModelScope())

    var youtubeUrl by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    val transcriptReceived by homeViewModel.transcriptCollected.collectAsState()
    val transcriptError by homeViewModel.error.collectAsState()

    val coroutineScope = rememberCoroutineScope()

    fun fetchTranscript(url: String) {
        homeViewModel.fetchTranscript(url)
    }

    fun onGetTranscriptClick() {
        if (youtubeUrl.isNotBlank()) {
            isLoading = true
            try {
                fetchTranscript(youtubeUrl)
            } catch (e: Exception) {

            } finally {
                isLoading = false
            }
        } else {
            /*Toast.makeText(context, "Please enter a URL", Toast.LENGTH_SHORT).show()*/
        }
    }

    Scaffold (
        topBar = {
            Spacer(modifier = Modifier.statusBarsPadding())
        }
    ) { paddingValues ->
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ProfileView {
                navigator.push(Screens.Profile)
            }

            LargeSpacer()

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

            Spacer(modifier = Modifier.height(16.dp))

            if (isLoading) {
                CircularProgressIndicator()
            }

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
        }
    }
}

