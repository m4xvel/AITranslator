package com.m4xvel.aitranslator.data.repository

import android.content.Context
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.m4xvel.aitranslator.db.Database
import com.m4xvel.aitranslator.domain.repository.LanguageSettingsRepository
import java.util.Locale

class LanguageSettingsRepositoryImpl(context: Context) : LanguageSettingsRepository {

    private val database = Database(AndroidSqliteDriver(Database.Schema, context, "database.db"))

    private val queries = database.languageQueries

    override fun saveLanguage(language: String, isActive: Boolean) {
        queries.saveLanguage(
            language = language,
            isActive = isActive
        )
    }

    override fun installLanguage(): String {
        return queries.installLanguage().executeAsOneOrNull()?.language
            ?: Locale.getDefault().language
    }
}