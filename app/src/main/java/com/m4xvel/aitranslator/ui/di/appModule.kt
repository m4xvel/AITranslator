package com.m4xvel.aitranslator.ui.di

import com.m4xvel.aitranslator.ui.screen.homeScreen.HomeScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { HomeScreenViewModel(get()) }
}