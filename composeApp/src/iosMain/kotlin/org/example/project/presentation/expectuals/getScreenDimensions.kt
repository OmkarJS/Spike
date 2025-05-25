package org.example.project.presentation.expectuals

import kotlinx.cinterop.ExperimentalForeignApi
import platform.UIKit.UIScreen

@OptIn(ExperimentalForeignApi::class)
actual fun getScreenHeight(): Float {
    return UIScreen.mainScreen.bounds.size.toInt().toFloat()
}

@OptIn(ExperimentalForeignApi::class)
actual fun getScreenWidth(): Float {
    return UIScreen.mainScreen.bounds.size.toInt().toFloat()
}