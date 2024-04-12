package com.m4xvel.aitranslator.ui.di

import com.m4xvel.aitranslator.MainViewModel
import com.m4xvel.aitranslator.ui.screen.util.repository.DefaultLanguageRepository
import com.m4xvel.aitranslator.ui.screen.util.repository.DefaultLanguageRepositoryImpl
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val uiModule = module {
    single<DefaultLanguageRepository> { DefaultLanguageRepositoryImpl(androidContext()) }
    viewModel { MainViewModel(get(), get(), get()) }
}