package com.m4xvel.aitranslator.domain.di

import com.m4xvel.aitranslator.data.repository.LanguageRepositoryImpl
import com.m4xvel.aitranslator.data.repository.TransferRepositoryImpl
import com.m4xvel.aitranslator.domain.repository.LanguageRepository
import com.m4xvel.aitranslator.domain.repository.TransferRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val domainModule = module {
    single<TransferRepository> { TransferRepositoryImpl(get()) }
    single<LanguageRepository> { LanguageRepositoryImpl(androidContext()) }
}