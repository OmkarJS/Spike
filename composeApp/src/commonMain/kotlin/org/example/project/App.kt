package org.example.project

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import org.example.project.presentation.navigation.MyAppNavigation
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        MyAppNavigation()
    }
}