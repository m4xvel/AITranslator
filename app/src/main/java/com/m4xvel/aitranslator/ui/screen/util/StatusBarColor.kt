package com.m4xvel.aitranslator.ui.screen.util

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import androidx.navigation.NavController
import com.m4xvel.aitranslator.ui.navigation.Screen

@Composable
fun StatusBarColor(
    navController: NavController,
    darkTheme: Boolean = isSystemInDarkTheme(),
) {
    val view = LocalView.current
    val pattern = Regex("[A-Z_A-Z]+(?=.*)")
    val matchResult = pattern.find(navController.currentDestination?.route.toString())
    val result = matchResult?.value

    val isActiveColor = MaterialTheme.colorScheme.surfaceContainerHigh.toArgb()
    val isNoActiveColor = MaterialTheme.colorScheme.background.toArgb()

    SideEffect {
        val window = (view.context as Activity).window
        if (result != Screen.HOME.name) {
            window.statusBarColor = isActiveColor
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        } else {
            window.statusBarColor = isNoActiveColor
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }
}