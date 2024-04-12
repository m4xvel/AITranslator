package com.m4xvel.aitranslator.ui.screen.languageSelectionScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.m4xvel.aitranslator.MainViewModel
import com.m4xvel.aitranslator.ui.screen.languageSelectionScreen.component.LanguageList
import com.m4xvel.aitranslator.ui.screen.languageSelectionScreen.component.SearchLanguagePanel
import com.m4xvel.aitranslator.ui.screen.languageSelectionScreen.component.UpperNavigationView

@Composable
fun LanguageSelectionScreen(
    navController: NavController,
    text: String,
    id: Int,
    viewModel: MainViewModel
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        UpperNavigationView(
            text = text,
            navController = navController
        )
        SearchLanguagePanel(viewModel = viewModel)
        LanguageList(
            language = viewModel.getAllLanguages(),
            id = id,
            navController = navController,
            viewModel = viewModel
        )
    }
}