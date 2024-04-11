package com.m4xvel.aitranslator.ui.screen.languageSelectionScreen

import androidx.lifecycle.ViewModel
import com.m4xvel.aitranslator.ui.screen.languageSelectionScreen.model.DataLanguageState
import com.m4xvel.aitranslator.ui.screen.util.GetAllLanguages
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LanguageSelectionScreenViewModel(
    private val getAllLanguages: GetAllLanguages
) : ViewModel() {

    private val _state = MutableStateFlow(DataLanguageState())
    val state: StateFlow<DataLanguageState> = _state.asStateFlow()

    fun setSearchText(text: String) {
        _state.update {
            it.copy(
                searchText = text
            )
        }
    }

    fun getAllLanguages(): List<String> {
        return getAllLanguages.getAllLanguages()
    }
}