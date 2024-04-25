package com.m4xvel.aitranslator.ui.screen.util.repository

import com.m4xvel.aitranslator.ui.theme.AppTheme

interface ThemeRepository {
    fun installTheme(themeId: Long): AppTheme
}