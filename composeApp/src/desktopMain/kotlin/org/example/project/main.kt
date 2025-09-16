package org.example.project

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.example.project.app.utils.KoinUtils

fun main() = application {
    KoinUtils.startKoin()
    Window(
        onCloseRequest = ::exitApplication,
        title = "Spike",
    ) {
        App()
    }
}