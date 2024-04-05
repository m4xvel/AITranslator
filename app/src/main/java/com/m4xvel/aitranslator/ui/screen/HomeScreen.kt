package com.m4xvel.aitranslator.ui.screen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(viewModel: HomeScreenViewModel = koinViewModel()) {
    val transferTextState by viewModel.transferTextState.collectAsState()
    Text(text = transferTextState)
}