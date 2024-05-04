package com.m4xvel.aitranslator.data.repository

import com.m4xvel.aitranslator.data.Driver
import com.m4xvel.aitranslator.domain.repository.ThemeSettingsRepository

class ThemeSettingsRepositoryImpl(driver: Driver) : ThemeSettingsRepository {

    private val queries = driver.database.themeQueries

    override fun saveTheme(themeId: Long) {
        queries.saveTheme(
            themeId = themeId
        )
    }

    override fun installTheme(): Long {
        return queries.installTheme().executeAsOneOrNull()?.themeId ?: 0
    }
}