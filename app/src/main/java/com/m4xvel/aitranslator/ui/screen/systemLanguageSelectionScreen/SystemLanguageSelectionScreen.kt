package com.m4xvel.aitranslator.ui.screen.systemLanguageSelectionScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.m4xvel.aitranslator.ui.screen.systemLanguageSelectionScreen.component.SystemLanguageList

@Composable
fun SystemLanguageSelectionScreen() {

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        SystemLanguageList()
    }
}