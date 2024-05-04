package com.m4xvel.aitranslator.data.repository

import com.m4xvel.aitranslator.data.Driver
import com.m4xvel.aitranslator.domain.repository.LanguageRepository

class LanguageRepositoryImpl(driver: Driver) : LanguageRepository {

    private val queries = driver.database.databaseQueries

    override fun insertLanguage(currentLanguage: String?, translationLanguage: String?) {
        queries.insertLanguage(
            currentLanguage = currentLanguage,
            translationLanguage = translationLanguage
        )
    }

    override fun selectCurrentLanguage(): String? {
        return queries.selectCurrentLanguage().executeAsOneOrNull()?.currentLanguage
    }

    override fun selectTranslationLanguage(): String? {
        return queries.selectTranslationLanguage().executeAsOneOrNull()?.translationLanguage
    }
}