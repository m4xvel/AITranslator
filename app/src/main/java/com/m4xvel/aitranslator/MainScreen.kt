package com.m4xvel.aitranslator

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.m4xvel.aitranslator.ui.navigation.AppNavHost
import com.m4xvel.aitranslator.ui.navigation.BottomNavBar
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val viewModel: MainViewModel = koinViewModel()
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
