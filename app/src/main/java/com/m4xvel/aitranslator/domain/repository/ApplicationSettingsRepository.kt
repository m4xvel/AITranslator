package com.m4xvel.aitranslator.domain.repository

interface ApplicationSettingsRepository {
    fun installTheme(): Long
    fun saveTheme(themeId: Long)
}