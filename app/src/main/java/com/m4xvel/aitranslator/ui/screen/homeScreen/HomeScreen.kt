package com.m4xvel.aitranslator.ui.screen.homeScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.m4xvel.aitranslator.ui.screen.homeScreen.components.LanguageSelectionPanel
import com.m4xvel.aitranslator.ui.theme.AITranslatorTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(viewModel: HomeScreenViewModel = koinViewModel()) {
    val transferTextState by viewModel.transferTextState.collectAsState()
    AITranslatorTheme {
        Box(modifier = Modifier.fillMaxSize()) {
            TranslationPanel()
        }
    }
}

@Composable
private fun TranslationPanel() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 5.dp, end = 5.dp, top = 20.dp)
    ) {
        LanguageSelectionPanel()
    }
}


