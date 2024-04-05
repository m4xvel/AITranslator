package com.m4xvel.aitranslator.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.m4xvel.aitranslator.domain.TransferRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeScreenViewModel(
private val transferRepository: TransferRepository
): ViewModel() {

    private val _transferTextState = MutableStateFlow("")
    val transferTextState: StateFlow<String> = _transferTextState

    init {
        showTransfer()
    }

    fun showTransfer() {
        viewModelScope.launch {
           _transferTextState.value = transferRepository.getTransfer().toString()
        }
    }
}