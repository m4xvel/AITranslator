package com.m4xvel.aitranslator.domain.repository

interface ThemeSettingsRepository {
    fun installTheme(): Long
    fun saveTheme(themeId: Long)
}