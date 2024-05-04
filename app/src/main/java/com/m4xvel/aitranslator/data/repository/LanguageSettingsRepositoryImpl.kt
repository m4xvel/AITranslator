package com.m4xvel.aitranslator.data.repository

import com.m4xvel.aitranslator.data.Driver
import com.m4xvel.aitranslator.domain.repository.LanguageSettingsRepository
import java.util.Locale

class LanguageSettingsRepositoryImpl(driver: Driver) : LanguageSettingsRepository {

    private val queries = driver.database.languageQueries

    override fun saveLanguage(language: String, isActive: Boolean) {
        queries.saveLanguage(
            language = language,
            isActive = isActive
        )
    }

    override fun installActive(): Boolean {
        return queries.installActive().executeAsOneOrNull()?.isActive ?: false
    }

    override fun installLanguage(): String {
        return queries.installLanguage().executeAsOneOrNull()?.language
            ?: Locale.getDefault().language
    }
}