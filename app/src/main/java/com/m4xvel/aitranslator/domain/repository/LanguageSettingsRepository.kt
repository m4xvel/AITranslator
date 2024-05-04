package com.m4xvel.aitranslator.domain.repository

interface LanguageSettingsRepository {
    fun installLanguage(): String
    fun installActive(): Boolean
    fun saveLanguage(language: String, isActive: Boolean)
}