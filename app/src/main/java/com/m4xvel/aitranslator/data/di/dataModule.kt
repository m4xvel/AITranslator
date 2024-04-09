package com.m4xvel.aitranslator.data.di

import com.m4xvel.aitranslator.data.ChatMessageProvider
import com.m4xvel.aitranslator.data.OpenAIClient
import com.m4xvel.aitranslator.db.Database
import org.koin.dsl.module

val dataModule = module {
    factory { OpenAIClient(get()) }
    factory { ChatMessageProvider() }
    single { Database(get()) }
}