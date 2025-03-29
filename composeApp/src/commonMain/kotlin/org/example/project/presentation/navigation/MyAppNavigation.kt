package org.example.project.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import cafe.adriel.voyager.navigator.CurrentScreen
import cafe.adriel.voyager.navigator.Navigator
import org.example.project.presentation.navigation.Screens

@Composable
fun MyAppNavigation() {
    val startDestination = remember { Screens.Home }

    Navigator(screen = startDestination) { navigator ->
        CurrentScreen()
    }
}