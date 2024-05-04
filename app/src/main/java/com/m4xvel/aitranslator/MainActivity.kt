package com.m4xvel.aitranslator

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import com.m4xvel.aitranslator.ui.model.DataState
import org.koin.androidx.compose.koinViewModel

val localMainViewModel = compositionLocalOf<MainViewModel> {
    error("No MainViewModel found!")
}
val localDataState = compositionLocalOf<DataState> {
    error("No DataState found!")
}

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel: MainViewModel = koinViewModel()
            val state by viewModel.state.collectAsState()
            CompositionLocalProvider(
                localMainViewModel provides viewModel,
                localDataState provides state
            ) {
                MainScreen()
            }
        }
    }
}