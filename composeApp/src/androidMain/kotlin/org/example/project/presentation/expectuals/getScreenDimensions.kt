package org.example.project.presentation.expectuals

import android.content.res.Resources

actual fun getScreenHeight(): Float {
    return Resources.getSystem().displayMetrics.heightPixels.toFloat()
}

actual fun getScreenWidth(): Float {
    return Resources.getSystem().displayMetrics.widthPixels.toFloat()
}