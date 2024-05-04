package com.m4xvel.aitranslator.ui.screen.systemLanguageSelectionScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.m4xvel.aitranslator.MainViewModel
import com.m4xvel.aitranslator.R
import com.m4xvel.aitranslator.ui.navigation.TopNavBar
import com.m4xvel.aitranslator.ui.screen.systemLanguageSelectionScreen.component.SystemLanguageList

@Composable
fun SystemLanguageSelectionScreen(
    navController: NavController
) {

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TopNavBar(
            text = stringResource(id = R.string.language_text),
            icon = {
                IconButton(onClick = {
                    navController.popBackStack()
                }) {
                    Icon(
                        modifier = Modifier.size(32.dp),
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        tint = Color.White,
                        contentDescription = "close"
                    )
                }
            }
        )
        SystemLanguageList()
    }
}