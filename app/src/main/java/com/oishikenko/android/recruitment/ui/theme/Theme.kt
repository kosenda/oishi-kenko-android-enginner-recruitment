package com.oishikenko.android.recruitment.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = mikanOrange,
    primaryVariant = onionBeige,
    secondary = blueberryBlue,
    background = backgroundDark,
    onBackground = textWeakColorDark,  // 弱い白文字として使用
    onSurface = textStrongColorDark    // 強い白文字として使用
)

private val LightColorPalette = lightColors(
    primary = apricotOrange,
    primaryVariant = onionBeige,
    secondary = blueberryBlue,
    background = backgroundLight,
    onBackground = textWeakColorLight, // 弱い黒文字として使用
    onSurface = textStrongColorLight   // 強い黒文字として使用
)

@Composable
fun RecruitmentTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}