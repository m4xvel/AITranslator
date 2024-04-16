package com.m4xvel.aitranslator.ui.model

import com.m4xvel.aitranslator.ui.screen.util.observerconnectivity.ConnectivityObserver

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

    //Network
    val statusNetwork: ConnectivityObserver.Status = ConnectivityObserver.Status.Unavailable,

    //LanguageSelectionScreen
    val searchLanguage: String = ""
)
