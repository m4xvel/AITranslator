package com.m4xvel.aitranslator.ui.screen.languageSelectionScreen

import androidx.lifecycle.ViewModel
import com.m4xvel.aitranslator.ui.screen.languageSelectionScreen.model.DataLanguageState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class LanguageSelectionScreenViewModel : ViewModel() {

    private val _state = MutableStateFlow(DataLanguageState())
    val state: StateFlow<DataLanguageState> = _state.asStateFlow()

}