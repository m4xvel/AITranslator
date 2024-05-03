package com.m4xvel.aitranslator.ui.di

import com.m4xvel.aitranslator.MainViewModel
import com.m4xvel.aitranslator.ui.screen.util.observerconnectivity.ConnectivityObserver
import com.m4xvel.aitranslator.ui.screen.util.observerconnectivity.NetworkConnectivityObserver
import com.m4xvel.aitranslator.ui.screen.util.repository.ChangeLanguageRepository
import com.m4xvel.aitranslator.ui.screen.util.repository.ChangeLanguageRepositoryImpl
import com.m4xvel.aitranslator.ui.screen.util.repository.DefaultLanguageRepository
import com.m4xvel.aitranslator.ui.screen.util.repository.DefaultLanguageRepositoryImpl
import com.m4xvel.aitranslator.ui.screen.util.repository.ThemeRepository
import com.m4xvel.aitranslator.ui.screen.util.repository.ThemeRepositoryImpl
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val uiModule = module {
    factory<DefaultLanguageRepository> { DefaultLanguageRepositoryImpl(androidContext()) }
    single<ConnectivityObserver> { NetworkConnectivityObserver(androidContext()) }
    single<ThemeRepository> { ThemeRepositoryImpl() }
    single<ChangeLanguageRepository> { ChangeLanguageRepositoryImpl(androidContext()) }
    viewModel { MainViewModel(get(), get(), get(), get(), get(), get()) }
}