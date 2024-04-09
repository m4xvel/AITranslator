package com.m4xvel.aitranslator.domain.repository

interface LanguageRepository {
    suspend fun insertLanguage(currentLanguage: String?)

    suspend fun selectCurrentLanguage(): String?

    suspend fun selectTranslationLanguage(): String?
}