package com.m4xvel.aitranslator.ui.screen.util

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

@Composable
fun StatusBarColor(
    statusBarColor: Color,
    isDark: Boolean = isSystemInDarkTheme()
) {

    val view = LocalView.current

    SideEffect {

        val window = (view.context as Activity).window

        window.statusBarColor = statusBarColor.toArgb()
        WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !isDark
    }
}
