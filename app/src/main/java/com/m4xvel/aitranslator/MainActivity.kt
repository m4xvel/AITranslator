package com.m4xvel.aitranslator

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import com.m4xvel.aitranslator.domain.repository.LanguageSettingsRepository
import com.m4xvel.aitranslator.ui.model.DataState
import com.m4xvel.aitranslator.ui.screen.util.repository.ChangeLanguageRepository
import com.m4xvel.aitranslator.ui.theme.AITranslatorTheme
import org.koin.androidx.compose.koinViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

val localMainViewModel = compositionLocalOf<MainViewModel> {
    error("No MainViewModel found!")
}
val localDataState = compositionLocalOf<DataState> {
    error("No DataState found!")
}

class MainActivity : AppCompatActivity(), KoinComponent {
    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        val changeLanguageRepository: ChangeLanguageRepository by inject()
        val languageSettingsRepository: LanguageSettingsRepository by inject()

        changeLanguageRepository.changeLanguage(languageSettingsRepository.installLanguage())

        setContent {

            val viewModel: MainViewModel = koinViewModel()
            val state by viewModel.state.collectAsState()

            CompositionLocalProvider(
                localMainViewModel provides viewModel,
                localDataState provides state
            ) {
                AITranslatorTheme(appTheme = localDataState.current.theme) {
                    MainScreen()
                }
            }
        }
    }
}