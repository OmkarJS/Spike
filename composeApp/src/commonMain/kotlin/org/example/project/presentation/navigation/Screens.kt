package org.example.project.presentation.navigation

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import org.example.project.presentation.home.HomePage
import org.example.project.presentation.profile.ProfilePage

sealed class Screens() : Screen {
    object Home : Screens() {
        @Composable
        override fun Content() {
            HomePage()
        }
    }

    object Profile : Screens() {
        @Composable
        override fun Content() {
            ProfilePage()
        }
    }
}