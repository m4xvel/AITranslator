package com.m4xvel.aitranslator.ui.di

import android.content.res.Configuration
import com.m4xvel.aitranslator.MainViewModel
import com.m4xvel.aitranslator.ui.screen.util.observerconnectivity.ConnectivityObserver
import com.m4xvel.aitranslator.ui.screen.util.observerconnectivity.NetworkConnectivityObserver
import com.m4xvel.aitranslator.ui.screen.util.repository.ChangeLanguageRepository
import com.m4xvel.aitranslator.ui.screen.util.repository.ChangeLanguageRepositoryImpl
import com.m4xvel.aitranslator.ui.screen.util.repository.ControlLanguageRepository
import com.m4xvel.aitranslator.ui.screen.util.repository.ControlLanguageRepositoryImpl
import com.m4xvel.aitranslator.ui.screen.util.repository.RestartAppRepository
import com.m4xvel.aitranslator.ui.screen.util.repository.RestartAppRepositoryImpl
import com.m4xvel.aitranslator.ui.screen.util.repository.ThemeRepository
import com.m4xvel.aitranslator.ui.screen.util.repository.ThemeRepositoryImpl
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import java.util.Locale

val uiModule = module {
    factory<ControlLanguageRepository> { (language: String) ->
        val original = androidContext()
        val config = Configuration().apply {
            setTo(original.resources.configuration)
            setLocale(Locale(language))
        }
        return@factory ControlLanguageRepositoryImpl(original.createConfigurationContext(config))
    }
    single<ConnectivityObserver> { NetworkConnectivityObserver(androidContext()) }
    single<ThemeRepository> { ThemeRepositoryImpl() }
    single<ChangeLanguageRepository> { ChangeLanguageRepositoryImpl(androidContext()) }
    single<RestartAppRepository> { RestartAppRepositoryImpl(androidContext()) }
    viewModel { MainViewModel(get(), get(), get(), get(), get(), get(), get()) }
}