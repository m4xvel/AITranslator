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
        getLanguage()
    }

    private fun showTransfer() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    transferText = transferRepository.getTransfer(
                        "${_state.value.currentLanguage}",
                        "${_state.value.translationLanguage}",
                        "The only way to do great work is to love what you do."
                    ).toString()
                )
            }
        }
    }

    private fun saveLanguage() {
        viewModelScope.launch {
            languageRepository.insertLanguage(
                _state.value.currentLanguageKey,
                _state.value.translationLanguageKey
            )
        }
    }

    private fun getLanguage() {
        viewModelScope.launch {
            val currentLanguage: String? = languageRepository.selectCurrentLanguage()
            val translationLanguage: String? = languageRepository.selectTranslationLanguage()
            if (currentLanguage != null && translationLanguage != null) {
                _state.update {
                    it.copy(
                        currentLanguageKey = currentLanguage,
                        currentLanguage = loadDefaultLanguage.getLocaleLanguage(
                            currentLanguage
                        ),
                        translationLanguageKey = translationLanguage,
                        translationLanguage = loadDefaultLanguage.getLocaleLanguage(
                            translationLanguage
                        )
                    )
                }
            } else {
                _state.update {
                    it.copy(
                        currentLanguageKey = loadDefaultLanguage.getDefaultCurrentLanguage(),
                        currentLanguage = loadDefaultLanguage.getLocaleLanguage(
                            loadDefaultLanguage.getDefaultCurrentLanguage()
                        ),
                        translationLanguageKey = loadDefaultLanguage.getDefaultTranslationLanguage(),
                        translationLanguage = loadDefaultLanguage.getLocaleLanguage(
                            loadDefaultLanguage.getDefaultTranslationLanguage()
                        )
                    )
                }
            }
        }
        saveLanguage()
    }

    fun swapLanguage() {
        _state.update {
            it.copy(
                currentLanguageKey = _state.value.translationLanguageKey,
                currentLanguage = loadDefaultLanguage.getLocaleLanguage(_state.value.translationLanguageKey!!),
                translationLanguageKey = _state.value.currentLanguageKey,
                translationLanguage = loadDefaultLanguage.getLocaleLanguage(_state.value.currentLanguageKey!!)
            )
        }
        saveLanguage()
    }
}