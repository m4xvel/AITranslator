package com.m4xvel.aitranslator.ui.animation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.m4xvel.aitranslator.localDataState
import com.m4xvel.aitranslator.localMainViewModel

@Composable
fun TranslateButton(
    isPlaying: Boolean,
    onClick: () -> Unit
) {

    val viewModel = localMainViewModel.current

    val state = localDataState.current

    val composition by rememberLottieComposition(spec = LottieCompositionSpec.Asset("animation-translate.json"))

    val interactionSource = remember { MutableInteractionSource() }

    val progress by animateLottieCompositionAsState(
        composition = composition,
        clipSpec = state.clipSpec,
        isPlaying = isPlaying,
        iterations = state.iterations
    )

    viewModel.updateAnimation(progress = progress)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .offset(y = (-30).dp)
            .zIndex(1f),
        contentAlignment = Alignment.Center,
        content = {
            LottieAnimation(
                composition = composition,
                progress = { progress },
                modifier = Modifier.clickable(
                    interactionSource = interactionSource,
                    indication = null,
                    onClick = onClick
                )
            )
        }
    )
}
