package com.m4xvel.aitranslator.data.repository

import android.content.Context
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.m4xvel.aitranslator.db.Database
import com.m4xvel.aitranslator.domain.repository.LanguageRepository

class LanguageRepositoryImpl(context: Context) : LanguageRepository {

    private val database = Database(AndroidSqliteDriver(Database.Schema, context, "database.db"))

    private val queries = database.databaseQueries
    override suspend fun insertLanguage(currentLanguage: String?) {
        queries.insertLanguage(
            currentLanguage = currentLanguage
        )
    }

    override suspend fun selectCurrentLanguage(): String? {
        return queries.selectCurrentLanguage().executeAsOneOrNull()?.currentLanguage
    }

    override suspend fun selectTranslationLanguage(): String? {
        return queries.selectCurrentLanguage().executeAsOneOrNull()?.currentLanguage
    }
}