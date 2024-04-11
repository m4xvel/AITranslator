package com.m4xvel.aitranslator.ui.di

import com.m4xvel.aitranslator.ui.screen.homeScreen.HomeScreenViewModel
import com.m4xvel.aitranslator.ui.screen.languageSelectionScreen.LanguageSelectionScreenViewModel
import com.m4xvel.aitranslator.ui.screen.util.GetAllLanguages
import com.m4xvel.aitranslator.ui.screen.util.GetAllLanguagesImpl
import com.m4xvel.aitranslator.ui.screen.util.LoadDefaultLanguage
import com.m4xvel.aitranslator.ui.screen.util.LoadDefaultLanguageImpl
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<GetAllLanguages> { GetAllLanguagesImpl(androidContext()) }
    single<LoadDefaultLanguage> { LoadDefaultLanguageImpl(androidContext()) }

    viewModel { HomeScreenViewModel(get(), get(), get()) }
    viewModel { LanguageSelectionScreenViewModel(get()) }
}