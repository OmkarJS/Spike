package org.example.project.presentation.expectuals

import java.awt.Toolkit

actual fun getScreenHeight(): Float {
    return Toolkit.getDefaultToolkit().screenSize.height
}

actual fun getScreenWidth(): Float {
    return Toolkit.getDefaultToolkit().screenSize.width
}