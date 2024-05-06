package com.m4xvel.aitranslator.ui.screen.util.repository

import android.app.Activity
import android.content.Context
import android.content.Intent
import com.m4xvel.aitranslator.MainActivity

class RestartAppRepositoryImpl(private val context: Context) : RestartAppRepository {
    override fun restart() {
        val intent = Intent(context, MainActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }
        context.startActivity(intent)
        (context as? Activity)?.finish()
    }
}