package org.example.project.app.expectuals

import kotlinx.browser.window

actual fun getScreenHeight(): Float {
    return window.innerHeight.toFloat()
}

actual fun getScreenWidth(): Float {
    return window.innerWidth.toFloat()
}