package com.m4xvel.aitranslator.ui.model

import com.airbnb.lottie.compose.LottieClipSpec
import com.m4xvel.aitranslator.ui.screen.util.observerconnectivity.ConnectivityObserver
import com.m4xvel.aitranslator.ui.theme.AppTheme

data class DataState(
    //HomeScreen
    val transferText: String = "",
    val currentLanguageKey: String? = null,
    val currentLanguage: String? = null,
    val translationLanguageKey: String? = null,
    val translationLanguage: String? = null,
    val leftButtonID: Int = 1,
    val rightButtonID: Int = 2,
    val showTranslationTextPanel: Boolean = false,
    val inputText: String = "",
    val isKeyboardVisible: Boolean = false,

    //Animation
    val isPlaying: Boolean = false,
    val clipSpec: LottieClipSpec.Progress = LottieClipSpec.Progress(min = 0f, max = 0.5f),
    val iterations: Int = 1,

    //Network
    val statusNetwork: ConnectivityObserver.Status = ConnectivityObserver.Status.Unavailable,

    //LanguageSelectionScreen
    val searchLanguage: String = "",

    //Theme
    val theme: AppTheme = AppTheme.Default,

    //SwitchButton
    val isChecked: Boolean = false
)
