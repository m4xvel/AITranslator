package com.m4xvel.aitranslator.ui.screen.homeScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.m4xvel.aitranslator.domain.TransferRepository
import com.m4xvel.aitranslator.ui.screen.util.LoadDefaultLanguage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeScreenViewModel(
    private val transferRepository: TransferRepository,
    private val loadDefaultLanguage: LoadDefaultLanguage
) : ViewModel() {

    private val _transferTextState = MutableStateFlow("")
    val transferTextState: StateFlow<String> = _transferTextState

    fun showTransfer() {
        viewModelScope.launch {
            _transferTextState.value = transferRepository.getTransfer(
                "English",
                "Russian",
                "The only way to do great work is to love what you do."
            ).toString()
        }
    }

    fun getCurrentLanguage(): String {
        return loadDefaultLanguage.loadDefaultLanguage()
    }
}