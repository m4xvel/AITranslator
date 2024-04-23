package com.m4xvel.aitranslator

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.m4xvel.aitranslator.ui.navigation.AppNavHost
import com.m4xvel.aitranslator.ui.navigation.BottomNavBar
import com.m4xvel.aitranslator.ui.theme.AITranslatorTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val viewModel: MainViewModel = koinViewModel()
    val state by viewModel.state.collectAsState()
    AITranslatorTheme(appTheme = state.theme) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Scaffold(
                bottomBar = {
                    BottomNavBar(navController = navController)
                },
                modifier = Modifier.fillMaxSize(),
                content = {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(it)
                    ) {
                        AppNavHost(
                            navController = navController,
                            viewModel = viewModel
                        )
                    }
                }
            )
        }
    }
}
