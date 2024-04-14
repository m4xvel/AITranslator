package com.m4xvel.aitranslator.ui.model

data class DataState(
    //HomeScreen
    val transferText: String? = null,
    val currentLanguageKey: String? = null,
    val currentLanguage: String? = null,
    val translationLanguageKey: String? = null,
    val translationLanguage: String? = null,
    val leftButtonID: Int = 1,
    val rightButtonID: Int = 2,

    val inputText: String = "",
    val isKeyboardVisible: Boolean = false,

    //Animation
    val isPlaying: Boolean = false,

    val showTranslationTextPanel: Boolean = false,

    //LanguageSelectionScreen
    val searchLanguage: String = ""
)
