package com.m4xvel.aitranslator.ui.screen.settingScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.m4xvel.aitranslator.ui.screen.settingScreen.component.SystemLanguageSelectionPanel
import com.m4xvel.aitranslator.ui.screen.settingScreen.component.ThemeSelectionPanel

@Composable
fun SettingScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp, start = 15.dp, end = 15.dp)
        ) {
            ThemeSelectionPanel()
            SystemLanguageSelectionPanel(navController = navController)
        }
    }
}