package org.example.project

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.*
import org.example.project.presentation.components.AppTheme
import org.example.project.presentation.navigation.MyAppNavigation
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    AppTheme(darkTheme = isSystemInDarkTheme()) {
        MyAppNavigation()
    }
}