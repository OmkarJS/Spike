package org.example.project.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color

data class ThemeColors(
    val primary: Color,
    val primaryVariant: Color,
    val secondary: Color,
    val background: Color,
    val surface: Color,
    val white: Color,
    val black: Color,
    val grey: Color,
    val onBackground: Color,
    val onSurface: Color
)

val LightColorScheme = ThemeColors(
    primary = Color(0xFF00796B), // Green
    primaryVariant = Color(0xFF3700B3), // Purple 700
    secondary = Color(0xFF03DAC6), // Teal 200
    background = Color(0xFFFFFFFF), // White
    surface = Color(0xFFFFFFFF), // White
    white = Color(0xFFFFFFFF),
    black = Color(0xFF000000),
    grey = Color(0xFF9E9E9E),
    onBackground = Color(0xFF000000), // Black text on background
    onSurface = Color(0xFF000000) // Black text on surface
)

val DarkColorScheme = ThemeColors(
    primary = Color(0xFF00796B), // Light Purple
    primaryVariant = Color(0xFF3700B3), // Purple 700
    secondary = Color(0xFF03DAC6), // Teal 200
    background = Color(0xFF121212), // Dark background
    surface = Color(0xFF1E1E1E), // Darker surface
    white = Color(0xFF000000),
    black = Color(0xFFFFFFFF),
    grey = Color(0xFFBDBDBD),
    onBackground = Color(0xFFFFFFFF), // White text on background
    onSurface = Color(0xFFFFFFFF) // White text on surface
)

fun ThemeColors.toColors() = lightColors(
    primary = primary,
    primaryVariant = primaryVariant,
    secondary = secondary,
    background = background,
    surface = surface,
    onPrimary = white,
    onSecondary = black,
    onBackground = onBackground,
    onSurface = onSurface
)

fun ThemeColors.toDarkColors() = darkColors(
    primary = primary,
    primaryVariant = primaryVariant,
    secondary = secondary,
    background = background,
    surface = surface,
    onPrimary = white,
    onSecondary = black,
    onBackground = onBackground,
    onSurface = onSurface
)

val LocalAppColors = compositionLocalOf { LightColorScheme }

@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColorScheme else LightColorScheme
    val materialColors = if (darkTheme) colors.toDarkColors() else colors.toColors()

    CompositionLocalProvider(LocalAppColors provides colors) {
        MaterialTheme(
            colors = materialColors,
            content = content
        )
    }
}