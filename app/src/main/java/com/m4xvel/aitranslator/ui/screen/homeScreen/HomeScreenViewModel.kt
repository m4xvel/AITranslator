package com.m4xvel.aitranslator.ui.screen.homeScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.m4xvel.aitranslator.domain.repository.LanguageRepository
import com.m4xvel.aitranslator.domain.repository.TransferRepository
import com.m4xvel.aitranslator.ui.screen.homeScreen.model.DataState
import com.m4xvel.aitranslator.ui.screen.util.LoadDefaultLanguage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeScreenViewModel(
    private val transferRepository: TransferRepository,
    private val loadDefaultLanguage: LoadDefaultLanguage,
    private val languageRepository: LanguageRepository
) : ViewModel() {

    private val _state = MutableStateFlow(DataState())
    val state: StateFlow<DataState> = _state.asStateFlow()

    init {
        getCurrentLanguage()
    }

    private fun showTransfer() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    transferText = transferRepository.getTransfer(
                        "English",
                        "Russian",
                        "The only way to do great work is to love what you do."
                    ).toString()
                )
            }
        }
    }

    private fun getCurrentLanguage() {
        viewModelScope.launch {
            val currentLanguage: String? = languageRepository.selectCurrentLanguage()
            if (currentLanguage != null) {
                _state.update { it.copy(currentLanguage = currentLanguage) }
                languageRepository.insertLanguage(_state.value.currentLanguage)
            } else {
                _state.update { it.copy(currentLanguage = loadDefaultLanguage.loadDefaultLanguage()) }
                languageRepository.insertLanguage(_state.value.currentLanguage)
            }
        }
    }
}