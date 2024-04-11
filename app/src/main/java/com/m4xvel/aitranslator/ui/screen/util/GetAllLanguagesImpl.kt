package com.m4xvel.aitranslator.ui.screen.util

import android.content.Context
import com.m4xvel.aitranslator.R

class GetAllLanguagesImpl(
    context: Context
) : GetAllLanguages {

    private val languagesList: List<String> = listOf(
        context.getString(R.string.language_chinese),
        context.getString(R.string.language_spanish),
        context.getString(R.string.language_french),
        context.getString(R.string.language_german),
        context.getString(R.string.language_russian),
        context.getString(R.string.language_japanese),
        context.getString(R.string.language_portuguese),
        context.getString(R.string.language_italian),
        context.getString(R.string.language_korean),
        context.getString(R.string.language_english),
    )

    override fun getAllLanguages(): List<String> {
        return languagesList
    }
}