package com.m4xvel.aitranslator.ui.screen.util.repository

import android.content.Context
import com.m4xvel.aitranslator.R
import java.util.Locale

class DefaultLanguageRepositoryImpl(
    context: Context
) : DefaultLanguageRepository {

    private val currentLanguage: String? = Locale.getDefault().language

    private val languageMap = mapOf(
        "zh" to context.getString(R.string.language_chinese),
        "es" to context.getString(R.string.language_spanish),
        "fr" to context.getString(R.string.language_french),
        "de" to context.getString(R.string.language_german),
        "ru" to context.getString(R.string.language_russian),
        "ja" to context.getString(R.string.language_japanese),
        "pt" to context.getString(R.string.language_portuguese),
        "it" to context.getString(R.string.language_italian),
        "ko" to context.getString(R.string.language_korean),
        "en" to context.getString(R.string.language_english),
    )

    override fun getAllLanguage(): Map<String, String> {
        return languageMap
    }

    override fun getDefaultCurrentLanguage(): String {
        return when (currentLanguage) {
            "zh" -> "zh"
            "es" -> "es"
            "fr" -> "fr"
            "de" -> "de"
            "ru" -> "ru"
            "ja" -> "ja"
            "pt" -> "pt"
            "it" -> "it"
            "ko" -> "ko"
            "en" -> "en"
            else -> "ru"
        }
    }

    override fun getDefaultTranslationLanguage(): String {
        return if (getDefaultCurrentLanguage() == "en") {
            "ru"
        } else {
            "en"
        }
    }

    override fun getLocaleLanguage(language: String): String? {
        return when (language) {
            "zh" -> languageMap["zh"]
            "es" -> languageMap["es"]
            "fr" -> languageMap["fr"]
            "de" -> languageMap["de"]
            "ru" -> languageMap["ru"]
            "ja" -> languageMap["ja"]
            "pt" -> languageMap["pt"]
            "it" -> languageMap["it"]
            "ko" -> languageMap["ko"]
            "en" -> languageMap["en"]
            else -> languageMap["ru"]
        }
    }
}
