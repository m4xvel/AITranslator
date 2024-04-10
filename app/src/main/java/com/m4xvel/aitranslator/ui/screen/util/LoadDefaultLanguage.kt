package com.m4xvel.aitranslator.ui.screen.util

interface LoadDefaultLanguage  {
    fun getDefaultCurrentLanguage(): String

    fun getDefaultTranslationLanguage(): String

    fun getLocaleLanguage(language: String): String?
}