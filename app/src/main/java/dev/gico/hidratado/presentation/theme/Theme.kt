package dev.gico.hidratado.presentation.theme

import androidx.compose.runtime.Composable
import androidx.wear.compose.material.Colors
import androidx.wear.compose.material.MaterialTheme

private val colors = Colors(
    primary = darkPrimary,
    primaryVariant = darkPrimaryVariant,
    secondary = darkSecondary,
    secondaryVariant = darkSecondaryVariant,
    background = darkBackground,
    surface = darkSurface,
    error = darkError,
    onPrimary = darkOnPrimary,
    onSecondary = darkOnSecondary,
    onBackground = darkOnBackground,
    onSurface = darkOnSurface,
    onSurfaceVariant = darkOnSurfaceVariant,
    onError = darkOnError
)

@Composable
fun AppTheme(
    content: @Composable() () -> Unit
) {
    MaterialTheme(
        colors = colors,
        content = content
    )
}
