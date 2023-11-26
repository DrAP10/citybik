package com.base.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorPalette = darkColors(
        primary = Blue200,
        primaryVariant = Blue700,
        secondary = Teal200
)

private val LightColorPalette = lightColors(
        primary = Blue500,
        primaryVariant = Blue700,
        secondary = Teal200
)

private val NoConnectionAvailableDarkColorPalette = darkColors(
    primary = Red200,
    primaryVariant = Red700,
    secondary = Teal200
)

private val NoConnectionAvailableLightColorPalette = lightColors(
        primary = Red200,
        primaryVariant = Red700,
        secondary = Teal200

        /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun BaseTheme(
    connectionAvailable: Boolean = true,
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val systemUiController = rememberSystemUiController()

    val colors = when {
        darkTheme && connectionAvailable -> {
            DarkColorPalette
        }
        darkTheme && !connectionAvailable -> {
            NoConnectionAvailableDarkColorPalette
        }
        !darkTheme && !connectionAvailable -> {
            NoConnectionAvailableLightColorPalette
        }
        else -> {
            LightColorPalette
        }
    }
    systemUiController.setSystemBarsColor(
        color = colors.primaryVariant
    )

    MaterialTheme(
            colors = colors,
            typography = Typography,
            shapes = Shapes,
            content = content
    )
}