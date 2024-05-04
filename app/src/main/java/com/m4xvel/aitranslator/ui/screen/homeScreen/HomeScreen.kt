package com.m4xvel.aitranslator.ui.screen.homeScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.m4xvel.aitranslator.localDataState
import com.m4xvel.aitranslator.localMainViewModel
import com.m4xvel.aitranslator.ui.animation.TranslateButton
import com.m4xvel.aitranslator.ui.screen.homeScreen.component.CurrentTextPanel
import com.m4xvel.aitranslator.ui.screen.homeScreen.component.InternetNoConnection
import com.m4xvel.aitranslator.ui.screen.homeScreen.component.LanguageSelectionPanel
import com.m4xvel.aitranslator.ui.screen.homeScreen.component.TranslationTextPanel
import com.m4xvel.aitranslator.ui.screen.util.observerconnectivity.ConnectivityObserver

@Composable
fun HomeScreen(navController: NavController) {
    Box(modifier = Modifier.fillMaxSize()) {
        TranslationPanel(navController = navController)
    }
}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
private fun TranslationPanel(navController: NavController) {

    val viewModel = localMainViewModel.current

    val state = localDataState.current

    val isConn = state.statusNetwork == ConnectivityObserver.Status.Available

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(start = 5.dp, end = 5.dp, top = 20.dp)
    ) {
        LanguageSelectionPanel(navController = navController)
        CurrentTextPanel()
        TranslateButton(
            isPlaying = localDataState.current.isPlaying,
            onClick = { viewModel.showTransfer() }
        )
        if (state.showTranslationTextPanel) {
            TranslationTextPanel()
        }
        if (!isConn) {
            InternetNoConnection()
        }
    }
}


