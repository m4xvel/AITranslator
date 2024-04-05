package com.m4xvel.aitranslator.domain.di

import com.m4xvel.aitranslator.data.repository.TransferRepositoryImpl
import com.m4xvel.aitranslator.domain.TransferRepository
import org.koin.dsl.module

val domainModule = module {
    single<TransferRepository> { TransferRepositoryImpl(get()) }
}