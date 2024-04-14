package com.m4xvel.aitranslator

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.m4xvel.aitranslator.domain.repository.LanguageRepository
import com.m4xvel.aitranslator.domain.repository.TransferRepository
import com.m4xvel.aitranslator.ui.model.DataState
import com.m4xvel.aitranslator.ui.screen.util.repository.DefaultLanguageRepository
import kotlinx.coroutines.delay
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

    fun showTransfer() {
        if (_state.value.inputText.isNotEmpty()) {
            try {
                viewModelScope.launch {
                    runAnimation(true)
                    _state.update {
                        it.copy(
                            transferText = transferRepository.getTransfer(
                                sourceText = "${_state.value.currentLanguage}",
                                translatedText = "${_state.value.translationLanguage}",
                                text = _state.value.inputText
                            ).toString()
                        )
                    }
                    showTranslationTextPanel()
                    runAnimation(false)
                    Log.d("!!!", "${_state.value.transferText}")
                }
            } catch (e: Exception) {
                Log.d("!!!", "Произошла ошибка: ${e.message}")
                runAnimation(false)
            }
        }
    }

    private fun runAnimation(start: Boolean) {
        _state.update {
            it.copy(
                isPlaying = start
            )
        }
    }

    private fun showTranslationTextPanel() {
        if (_state.value.inputText.isNotEmpty()) {
            _state.update {
                it.copy(
                    showTranslationTextPanel = true
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

    fun decreaseFont(): Int {
        val textLength = _state.value.inputText.length
        return when {
            textLength <= 60 -> 24
            textLength <= 140 -> 20
            else -> 16
        }
    }

    fun deleteText() {
        _state.update { it.copy(inputText = "") }
    }

    fun pasteText(text: String) {
        _state.update { it.copy(inputText = text) }
    }



}