package com.m4xvel.aitranslator.ui.screen.settingScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.m4xvel.aitranslator.MainViewModel
import com.m4xvel.aitranslator.ui.navigation.TopNavBar
import com.m4xvel.aitranslator.ui.screen.settingScreen.component.ThemeSelectionPanel

@Composable
fun SettingScreen(
    viewModel: MainViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        TopNavBar(text = "Настройки")
        ThemeSelectionPanel(viewModel = viewModel)
    }
}