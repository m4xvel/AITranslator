package com.m4xvel.aitranslator.data.repository

import android.content.Context
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.m4xvel.aitranslator.db.Database
import com.m4xvel.aitranslator.domain.repository.ThemeSettingsRepository
import java.util.Locale

class ThemeSettingsRepositoryImpl(context: Context) : ThemeSettingsRepository {

    private val database = Database(AndroidSqliteDriver(Database.Schema, context, "database.db"))

    private val queries = database.themeQueries

    override fun saveTheme(themeId: Long) {
        queries.saveTheme(
            themeId = themeId
        )
    }

    override fun installTheme(): Long {
        return queries.installTheme().executeAsOneOrNull()?.themeId ?: 0
    }
}