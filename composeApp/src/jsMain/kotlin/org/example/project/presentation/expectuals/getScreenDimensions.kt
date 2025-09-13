package org.example.project.presentation.expectuals

import kotlinx.browser.window

actual fun getScreenHeight(): Float {
    return window.innerHeight.toFloat()
}

actual fun getScreenWidth(): Float {
    return window.innerWidth.toFloat()
}