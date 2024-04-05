package com.m4xvel.aitranslator.data.di

import com.m4xvel.aitranslator.data.OpenAIClient
import org.koin.dsl.module

val dataModule = module {
    factory { OpenAIClient() }
}