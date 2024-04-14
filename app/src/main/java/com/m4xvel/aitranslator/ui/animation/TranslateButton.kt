package com.m4xvel.aitranslator.ui.animation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieClipSpec
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun TranslateButton(
    isPlaying: Boolean,
    onClick: () -> Unit
) {

    val composition by rememberLottieComposition(spec = LottieCompositionSpec.Asset("animation-translate.json"))

    var clipSpec by remember { mutableStateOf(LottieClipSpec.Progress(min = 0f, max = 0.5f)) }
    var iterations by remember { mutableIntStateOf(1) }

    val interactionSource = remember { MutableInteractionSource() }

    val progress by animateLottieCompositionAsState(
        composition = composition,
        clipSpec = clipSpec,
        isPlaying = isPlaying,
        iterations = iterations,

        )

    if (progress == 0.5f) {
        clipSpec = LottieClipSpec.Progress(min = 0.55f, max = 1f)
        iterations = LottieConstants.IterateForever
    }

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
