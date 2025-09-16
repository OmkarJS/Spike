package org.example.project.app.expectuals

import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.useContents
import platform.UIKit.UIScreen

@OptIn(ExperimentalForeignApi::class)
actual fun getScreenHeight(): Float {
    return UIScreen.mainScreen.bounds.useContents { size.height.toFloat() * density() }
}

@OptIn(ExperimentalForeignApi::class)
actual fun getScreenWidth(): Float {
    return UIScreen.mainScreen.bounds.useContents { size.width.toFloat() * density() }
}

private fun density(): Float {
    return UIScreen.mainScreen.scale.toFloat()
}