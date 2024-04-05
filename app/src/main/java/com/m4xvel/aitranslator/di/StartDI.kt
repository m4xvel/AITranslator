package com.m4xvel.aitranslator.di

import android.app.Application
import com.m4xvel.aitranslator.data.di.dataModule
import com.m4xvel.aitranslator.domain.di.domainModule
import com.m4xvel.aitranslator.ui.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class StartDI : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@StartDI)
            modules(dataModule + domainModule + appModule)
        }
    }
}