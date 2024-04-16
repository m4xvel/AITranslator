package com.m4xvel.aitranslator.ui.screen.homeScreen

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.m4xvel.aitranslator.MainViewModel
import com.m4xvel.aitranslator.ui.animation.TranslateButton
import com.m4xvel.aitranslator.ui.screen.homeScreen.component.CurrentTextPanel
import com.m4xvel.aitranslator.ui.screen.homeScreen.component.InternetNoConnection
import com.m4xvel.aitranslator.ui.screen.homeScreen.component.LanguageSelectionPanel
import com.m4xvel.aitranslator.ui.screen.homeScreen.component.TranslationTextPanel
import com.m4xvel.aitranslator.ui.screen.util.StatusBarColor
import com.m4xvel.aitranslator.ui.screen.util.observerconnectivity.ConnectivityObserver
import com.m4xvel.aitranslator.ui.theme.AITranslatorTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.async

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: MainViewModel
) {
    StatusBarColor(navController = navController)
    AITranslatorTheme {
        Box(modifier = Modifier.fillMaxSize()) {
            TranslationPanel(
                navController = navController,
                viewModel = viewModel
            )
        }
    }
}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
private fun TranslationPanel(
    navController: NavController,
    viewModel: MainViewModel
) {
    val state by viewModel.state.collectAsState()

    viewModel.statusNetwork()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(start = 5.dp, end = 5.dp, top = 20.dp)
    ) {
        LanguageSelectionPanel(
            navController = navController,
            viewModel = viewModel
        )
        CurrentTextPanel(
            viewModel = viewModel
        )
        TranslateButton(
            isPlaying = state.isPlaying,
            onClick = {
                viewModel.showTransfer()
            }
        )
        if (state.showTranslationTextPanel) {
            TranslationTextPanel(
                viewModel = viewModel
            )
        }
        when (state.statusNetwork) {
            ConnectivityObserver.Status.Lost -> InternetNoConnection()
            ConnectivityObserver.Status.Unavailable -> InternetNoConnection()
            else -> { }
        }
    }
}


