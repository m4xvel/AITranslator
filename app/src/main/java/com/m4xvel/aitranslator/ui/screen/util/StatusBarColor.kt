package com.m4xvel.aitranslator.ui.screen.util

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import androidx.navigation.NavController
import com.m4xvel.aitranslator.ui.navigation.Screen
import com.m4xvel.aitranslator.ui.theme.LightBackground
import com.m4xvel.aitranslator.ui.theme.SecondaryColor

@Composable
fun StatusBarColor(
    navController: NavController,
    darkTheme: Boolean = isSystemInDarkTheme(),
) {
    val view = LocalView.current
    val pattern = Regex("[A-Z_A-Z]+(?=.*)")
    val matchResult = pattern.find(navController.currentDestination?.route.toString())
    val result = matchResult?.value
    SideEffect {
        val window = (view.context as Activity).window
        if (result == Screen.LANGUAGE_SELECTION.name) {
            window.statusBarColor = SecondaryColor.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        } else {
            window.statusBarColor = LightBackground.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }
}