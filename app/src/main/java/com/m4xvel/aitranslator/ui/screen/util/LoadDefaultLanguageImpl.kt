package com.m4xvel.aitranslator.ui.screen.util

import android.content.Context
import com.m4xvel.aitranslator.R
import java.util.Locale

class LoadDefaultLanguageImpl(
    private val context: Context
) : LoadDefaultLanguage {

    private val currentLanguage: String? = Locale.getDefault().language
    override fun loadDefaultLanguage(): String {
        return when(currentLanguage) {
            "zh" -> context.getString(R.string.language_chinese)
            "es" -> context.getString(R.string.language_spanish)
            "fr" -> context.getString(R.string.language_french)
            "de" -> context.getString(R.string.language_german)
            "ru" -> context.getString(R.string.language_russian)
            "ja" -> context.getString(R.string.language_japanese)
            "pt" -> context.getString(R.string.language_portuguese)
            "it" -> context.getString(R.string.language_italian)
            "ko" -> context.getString(R.string.language_korean)
            "en" -> context.getString(R.string.language_english)
            else -> context.getString(R.string.language_russian)
        }
    }

}