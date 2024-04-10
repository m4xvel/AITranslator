package com.m4xvel.aitranslator.ui.screen.languageSelectionScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.m4xvel.aitranslator.ui.screen.languageSelectionScreen.component.UpperNavigationView

@Composable
fun LanguageSelectionScreen(
    navController: NavController,
    text: String
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        UpperNavigationView(
            text = text,
            navController = navController
        )
    }
}