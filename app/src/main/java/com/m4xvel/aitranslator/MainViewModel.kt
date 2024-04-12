package com.m4xvel.aitranslator

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.m4xvel.aitranslator.domain.repository.LanguageRepository
import com.m4xvel.aitranslator.domain.repository.TransferRepository
import com.m4xvel.aitranslator.ui.model.DataState
import com.m4xvel.aitranslator.ui.screen.util.repository.DefaultLanguageRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel(
    private val transferRepository: TransferRepository,
    private val defaultLanguageRepository: DefaultLanguageRepository,
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
            Log.d("!!!", "${_state.value.transferText}")
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
                        currentLanguage = defaultLanguageRepository.getLocaleLanguage(
                            currentLanguage
                        ),
                        translationLanguageKey = translationLanguage,
                        translationLanguage = defaultLanguageRepository.getLocaleLanguage(
                            translationLanguage
                        )
                    )
                }
            } else {
                _state.update {
                    it.copy(
                        currentLanguageKey = defaultLanguageRepository.getDefaultCurrentLanguage(),
                        currentLanguage = defaultLanguageRepository.getLocaleLanguage(
                            defaultLanguageRepository.getDefaultCurrentLanguage()
                        ),
                        translationLanguageKey = defaultLanguageRepository.getDefaultTranslationLanguage(),
                        translationLanguage = defaultLanguageRepository.getLocaleLanguage(
                            defaultLanguageRepository.getDefaultTranslationLanguage()
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
                currentLanguage = defaultLanguageRepository.getLocaleLanguage(_state.value.translationLanguageKey!!),
                translationLanguageKey = _state.value.currentLanguageKey,
                translationLanguage = defaultLanguageRepository.getLocaleLanguage(_state.value.currentLanguageKey!!)
            )
        }
        saveLanguage()
    }

    fun updateCurrentLanguage(languageKey: String?) {
        _state.update {
            it.copy(
                currentLanguageKey = languageKey,
                currentLanguage = defaultLanguageRepository.getLocaleLanguage(languageKey!!),
            )
        }
        saveLanguage()
    }

    fun updateTranslationLanguage(languageKey: String?) {
        _state.update {
            it.copy(
                translationLanguageKey = languageKey,
                translationLanguage = defaultLanguageRepository.getLocaleLanguage(languageKey!!),
            )
        }
        saveLanguage()
    }

    fun setSearchLanguage(text: String) {
        _state.update {
            it.copy(
                searchLanguage = text
            )
        }
    }

    fun setInputText(text: String) {
        _state.update {
            it.copy(
                inputText = text
            )
        }
    }

    fun getAllLanguages(): Map<String, String> {
        return defaultLanguageRepository.getAllLanguage()
    }
}