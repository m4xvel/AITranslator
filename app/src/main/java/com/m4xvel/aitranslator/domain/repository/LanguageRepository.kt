package com.m4xvel.aitranslator.domain.repository

interface LanguageRepository {
    fun insertLanguage(currentLanguage: String?, translationLanguage: String?)
    fun selectCurrentLanguage(): String?
    fun selectTranslationLanguage(): String?
}