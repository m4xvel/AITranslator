package com.m4xvel.aitranslator.ui.screen.util

import android.view.ViewTreeObserver
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.platform.LocalView
import com.m4xvel.aitranslator.localMainViewModel

@Composable
fun KeyboardListener() {

    val viewModel = localMainViewModel.current

    val view = LocalView.current
    val viewTreeObserver = view.viewTreeObserver

    DisposableEffect(viewTreeObserver) {
        val listener = ViewTreeObserver.OnGlobalLayoutListener {
            viewModel.isKeyboardVisible(view)
        }
        viewTreeObserver.addOnGlobalLayoutListener(listener)
        onDispose {
            viewTreeObserver.removeOnGlobalLayoutListener(listener)
        }
    }
}