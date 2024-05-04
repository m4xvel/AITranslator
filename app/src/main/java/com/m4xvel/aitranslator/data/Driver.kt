package com.m4xvel.aitranslator.data

import android.content.Context
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.m4xvel.aitranslator.db.Database

class Driver(context: Context) {
    val database = Database(AndroidSqliteDriver(Database.Schema, context, "database.db"))
}