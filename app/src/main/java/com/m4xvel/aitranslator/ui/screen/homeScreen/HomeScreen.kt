package com.m4xvel.aitranslator.ui.screen.homeScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.m4xvel.aitranslator.ui.screen.homeScreen.component.LanguageSelectionPanel
import com.m4xvel.aitranslator.ui.theme.AITranslatorTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(viewModel: HomeScreenViewModel = koinViewModel()) {
    AITranslatorTheme {
        Box(modifier = Modifier.fillMaxSize()) {
            TranslationPanel(viewModel = viewModel)
        }
    }
}

@Composable
private fun TranslationPanel(viewModel: HomeScreenViewModel) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 5.dp, end = 5.dp, top = 20.dp)
    ) {
        LanguageSelectionPanel(viewModel = viewModel)
    }
}


