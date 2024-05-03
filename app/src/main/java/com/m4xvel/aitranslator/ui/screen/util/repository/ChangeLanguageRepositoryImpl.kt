package com.m4xvel.aitranslator.ui.screen.util.repository

import android.app.LocaleManager
import android.content.Context
import android.os.Build
import android.os.LocaleList
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat

class ChangeLanguageRepositoryImpl(
    private val context: Context
) : ChangeLanguageRepository {
    override fun changeLanguage(language: String) {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.TIRAMISU) {
            context.getSystemService(LocaleManager::class.java)
                .applicationLocales = LocaleList.forLanguageTags(language)
        } else {
            AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags(language))
        }
    }
}