package com.m4xvel.aitranslator.ui.screen.util.repository

interface ControlLanguageRepository  {

    fun getAllLanguage(): Map<String, String>

    fun getDefaultCurrentLanguage(): String

    fun getDefaultTranslationLanguage(): String

    fun getLocaleLanguage(language: String): String?
}