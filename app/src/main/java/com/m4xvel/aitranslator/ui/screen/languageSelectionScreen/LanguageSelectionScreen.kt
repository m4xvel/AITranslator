package com.m4xvel.aitranslator.ui.screen.languageSelectionScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.m4xvel.aitranslator.localMainViewModel
import com.m4xvel.aitranslator.ui.navigation.TopNavBar
import com.m4xvel.aitranslator.ui.screen.languageSelectionScreen.component.LanguageList
import com.m4xvel.aitranslator.ui.screen.languageSelectionScreen.component.SearchLanguagePanel

@Composable
fun LanguageSelectionScreen(
    navController: NavController,
    text: String,
    id: Int
) {

    val viewModel = localMainViewModel.current

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TopNavBar(
            text = text,
            icon = {
                IconButton(onClick = {
                    navController.popBackStack()
                }) {
                    Icon(
                        modifier = Modifier.size(32.dp),
                        imageVector = Icons.Default.Close,
                        tint = Color.White,
                        contentDescription = "close"
                    )
                }
            }
        )
        SearchLanguagePanel()
        LanguageList(
            language = viewModel.getAllLanguages(),
            id = id,
            navController = navController
        )
    }
}