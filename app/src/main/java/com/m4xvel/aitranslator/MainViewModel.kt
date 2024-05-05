package com.m4xvel.aitranslator

import android.util.Log
import android.view.View
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.airbnb.lottie.compose.LottieClipSpec
import com.airbnb.lottie.compose.LottieConstants
import com.m4xvel.aitranslator.domain.repository.LanguageRepository
import com.m4xvel.aitranslator.domain.repository.LanguageSettingsRepository
import com.m4xvel.aitranslator.domain.repository.ThemeSettingsRepository
import com.m4xvel.aitranslator.domain.repository.TransferRepository
import com.m4xvel.aitranslator.ui.model.DataState
import com.m4xvel.aitranslator.ui.screen.util.observerconnectivity.ConnectivityObserver
import com.m4xvel.aitranslator.ui.screen.util.repository.ControlLanguageRepository
import com.m4xvel.aitranslator.ui.screen.util.repository.RestartAppRepository
import com.m4xvel.aitranslator.ui.screen.util.repository.ThemeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.parameter.parametersOf

class MainViewModel(
    private val transferRepository: TransferRepository,
    private val languageRepository: LanguageRepository,
    private val connectivityObserver: ConnectivityObserver,
    private val themeRepository: ThemeRepository,
    private val themeSettingsRepository: ThemeSettingsRepository,
    private val languageSettingsRepository: LanguageSettingsRepository,
    private val restartAppRepository: RestartAppRepository
) : ViewModel(), KoinComponent {

    private val _state = MutableStateFlow(DataState())
    val state: StateFlow<DataState> = _state.asStateFlow()

    private val controlLanguageRepository: ControlLanguageRepository by inject {
        parametersOf(
            languageSettingsRepository.installLanguage()
        )
    }

    init {
        getLanguage()
        statusNetwork()
        setTheme()
        _state.update {
            it.copy(
                isChecked = languageSettingsRepository.installActive()
            )
        }
    }

    fun showTransfer() {
        if (_state.value.inputText.isNotEmpty()
            && _state.value.statusNetwork == ConnectivityObserver.Status.Available
        ) {
            try {
                viewModelScope.launch {
                    runAnimation(true)
                    _state.update {
                        it.copy(
                            transferText = transferRepository.getTransfer(
                                sourceText = "${_state.value.currentLanguage}",
                                translatedText = "${_state.value.translationLanguage}",
                                text = _state.value.inputText,
                            ).toString(),
                            showTranslationTextPanel = true
                        )
                    }
                    showTranslationTextPanel()
                    runAnimation(false)
                    Log.d("!!!", _state.value.transferText)
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
        val currentLanguage: String? = languageRepository.selectCurrentLanguage()
        val translationLanguage: String? = languageRepository.selectTranslationLanguage()
        if (currentLanguage != null && translationLanguage != null) {
            _state.update {
                it.copy(
                    currentLanguageKey = currentLanguage,
                    currentLanguage = controlLanguageRepository.getLocaleLanguage(
                        currentLanguage
                    ),
                    translationLanguageKey = translationLanguage,
                    translationLanguage = controlLanguageRepository.getLocaleLanguage(
                        translationLanguage
                    )
                )
            }
        } else {
            _state.update {
                it.copy(
                    currentLanguageKey = controlLanguageRepository.getDefaultCurrentLanguage(),
                    currentLanguage = controlLanguageRepository.getLocaleLanguage(
                        controlLanguageRepository.getDefaultCurrentLanguage()
                    ),
                    translationLanguageKey = controlLanguageRepository.getDefaultTranslationLanguage(),
                    translationLanguage = controlLanguageRepository.getLocaleLanguage(
                        controlLanguageRepository.getDefaultTranslationLanguage()
                    )
                )
            }
        }
        saveLanguage()
    }

    fun swapLanguage() {
        _state.update {
            it.copy(
                currentLanguageKey = _state.value.translationLanguageKey,
                currentLanguage = controlLanguageRepository.getLocaleLanguage(_state.value.translationLanguageKey!!),
                translationLanguageKey = _state.value.currentLanguageKey,
                translationLanguage = controlLanguageRepository.getLocaleLanguage(_state.value.currentLanguageKey!!)
            )
        }
        if (_state.value.showTranslationTextPanel) {
            _state.update {
                it.copy(
                    inputText = _state.value.transferText,
                    transferText = _state.value.inputText
                )
            }
        }
        saveLanguage()
    }

    fun updateCurrentLanguage(languageKey: String?) {
        _state.update {
            it.copy(
                currentLanguageKey = languageKey,
                currentLanguage = controlLanguageRepository.getLocaleLanguage(languageKey!!),
            )
        }
        saveLanguage()
    }

    fun updateTranslationLanguage(languageKey: String?) {
        _state.update {
            it.copy(
                translationLanguageKey = languageKey,
                translationLanguage = controlLanguageRepository.getLocaleLanguage(languageKey!!),
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
        return controlLanguageRepository.getAllLanguage()
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
        _state.update {
            it.copy(
                inputText = it.inputText + text
            )
        }
    }

    fun isKeyboardVisible(view: View) {
        val isKeyboardOpen = ViewCompat.getRootWindowInsets(view)
            ?.isVisible(WindowInsetsCompat.Type.ime()) ?: true
        _state.update { it.copy(isKeyboardVisible = isKeyboardOpen) }
    }

    fun statusNetwork() {
        viewModelScope.launch(Dispatchers.Unconfined) {
            connectivityObserver.observe().collect { status ->
                _state.update { it.copy(statusNetwork = status) }
                if (_state.value.statusNetwork != ConnectivityObserver.Status.Available) {
                    _state.update { it.copy(showTranslationTextPanel = false) }
                } else if (_state.value.statusNetwork == ConnectivityObserver.Status.Available
                    && _state.value.transferText.isNotEmpty()
                ) {
                    _state.update { it.copy(showTranslationTextPanel = true) }
                }
            }
        }
    }

    fun updateAnimation(progress: Float) {
        if (progress == 0.5f) {
            _state.update {
                it.copy(
                    clipSpec = LottieClipSpec.Progress(min = 0.55f, max = 1f),
                    iterations = LottieConstants.IterateForever
                )
            }
        }
    }

    fun setTheme(themeId: Long = themeSettingsRepository.installTheme()) {
        _state.update {
            it.copy(
                theme = themeRepository.installTheme(themeId)
            )
        }
        themeSettingsRepository.saveTheme(themeId)
    }

    fun switch(language: String) {
        if (!_state.value.isChecked) {
            _state.update {
                it.copy(
                    isChecked = true,
                    currentSystemLanguage = language
                )
            }
        } else {
            _state.update {
                it.copy(
                    isChecked = false
                )
            }
        }
    }

    fun restartApp(language: String) {
        languageSettingsRepository.saveLanguage(language, _state.value.isChecked)
        restartAppRepository.restart()
    }

    fun updateSystemLanguage(language: String) {
        _state.update {
            it.copy(
                currentSystemLanguage = language
            )
        }
    }

    fun currentLanguage(): String {
        return controlLanguageRepository.getLocaleLanguage(
            languageSettingsRepository.installLanguage()
        ).toString()
    }
}