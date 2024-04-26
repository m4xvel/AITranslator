package com.m4xvel.aitranslator.ui.screen.settingScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.m4xvel.aitranslator.MainViewModel
import com.m4xvel.aitranslator.ui.navigation.TopNavBar
import com.m4xvel.aitranslator.ui.screen.settingScreen.component.SystemLanguageSelectionPanel
import com.m4xvel.aitranslator.ui.screen.settingScreen.component.ThemeSelectionPanel

@Composable
fun SettingScreen(
    viewModel: MainViewModel,
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        TopNavBar(text = "Настройки")
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp, start = 15.dp, end = 15.dp)
        ) {
            ThemeSelectionPanel(viewModel = viewModel)
            SystemLanguageSelectionPanel(
                viewModel = viewModel,
                navController = navController
            )
        }
    }
}