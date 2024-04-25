package com.m4xvel.aitranslator.ui.screen.util.repository

import com.m4xvel.aitranslator.ui.theme.AppTheme

class ThemeRepositoryImpl : ThemeRepository {
    override fun installTheme(themeId: Long): AppTheme {
        return when (themeId) {
            1L -> AppTheme.Light
            2L -> AppTheme.Dark
            else -> AppTheme.Default
        }
    }
}