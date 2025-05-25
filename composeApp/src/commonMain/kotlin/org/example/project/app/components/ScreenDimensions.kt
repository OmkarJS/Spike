package org.example.project.app.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import org.example.project.presentation.expectuals.getScreenHeight
import org.example.project.presentation.expectuals.getScreenWidth

private const val halfValue = 0.5f

@Composable
fun fullScreenHeight(): Dp = pxToDp(getScreenHeight())

@Composable
fun halfScreenHeight(): Dp = fullScreenHeight() * halfValue

@Composable
fun quarterScreenHeight(): Dp = halfScreenHeight() * halfValue

@Composable
fun percentOfScreenHeight(percent: Int): Dp = fullScreenHeight() * (percent / 100f)


@Composable
fun fullScreenWidth(): Dp = pxToDp(getScreenWidth())

@Composable
fun halfScreenWidth(): Dp = fullScreenWidth() * halfValue

@Composable
fun quarterScreenWidth(): Dp = halfScreenWidth() * halfValue

@Composable
fun percentOfScreenWidth(percent: Int): Dp = fullScreenWidth() * (percent / 100f)


/*@Composable
fun screenOrientation() = LocalConfiguration.current.orientation

@Composable
fun isLandscape(): Boolean = screenOrientation() == Configuration.ORIENTATION_LANDSCAPE

@Composable
fun isPortrait(): Boolean = screenOrientation() == Configuration.ORIENTATION_PORTRAIT*/

@Composable
fun dpToPx(dp: Dp): Float = with(LocalDensity.current) { dp.toPx() }

@Composable
fun pxToDp(px: Float): Dp = with(LocalDensity.current) { px.toDp() }