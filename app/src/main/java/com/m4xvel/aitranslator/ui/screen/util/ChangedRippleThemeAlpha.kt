package com.m4xvel.aitranslator.ui.screen.util

import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

class ChangedRippleThemeAlpha(
    private val color: Color,
    private val rippleAlpha: RippleAlpha
): RippleTheme {
    @Composable
    override fun defaultColor(): Color = color

    @Composable
    override fun rippleAlpha(): RippleAlpha = rippleAlpha
}