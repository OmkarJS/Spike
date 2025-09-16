package org.example.project.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import cafe.adriel.voyager.navigator.CurrentScreen
import cafe.adriel.voyager.navigator.Navigator

@Composable
fun MyAppNavigation() {
    val startDestination = remember { Screens.HomePage }

    Navigator(screen = startDestination) {
        CurrentScreen()
    }
}