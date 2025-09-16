package org.example.project

import org.example.project.app.utils.KoinUtils
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Text
import org.jetbrains.compose.web.renderComposable

fun main() {
    KoinUtils.startKoin()

    renderComposable(rootElementId = "root") {
        /*App()*/
        Div {
            Text("Hello from Compose Web!")
        }
    }
}