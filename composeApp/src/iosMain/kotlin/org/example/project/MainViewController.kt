package org.example.project

import androidx.compose.ui.window.ComposeUIViewController
import org.example.project.app.utils.KoinUtils
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController {
    KoinUtils.startKoin()

    return ComposeUIViewController { App() }
}