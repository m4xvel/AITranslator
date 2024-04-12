package com.m4xvel.aitranslator.ui.screen.languageSelectionScreen.component

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.m4xvel.aitranslator.MainViewModel
import com.m4xvel.aitranslator.ui.navigation.Screen
import com.m4xvel.aitranslator.ui.theme.PrimaryColor
import com.m4xvel.aitranslator.ui.theme.RippleColor

@Composable
fun LanguageList(
    language: Map<String, String>,
    id: Int,
    navController: NavController,
    viewModel: MainViewModel
) {

    val state by viewModel.state.collectAsState()

    val languageFilter = language.filterValues { it.contains(state.searchText) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 21.dp)
            .verticalScroll(rememberScrollState())
    ) {
        languageFilter.forEach { language ->

            val interactionSource = remember { MutableInteractionSource() }
            val isPressed = interactionSource.collectIsPressedAsState().value

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(41.dp)
                    .clickable(
                        interactionSource = interactionSource,
                        indication = LocalIndication.current
                    ) {
                        if (id == state.leftButtonID) {
                            viewModel.updateCurrentLanguage(languageKey = language.key)
                        } else {
                            viewModel.updateTranslationLanguage(languageKey = language.key)
                        }
                        navController.navigate(Screen.HOME.name) {
                            popUpTo(0)
                        }
                    },
                contentAlignment = Alignment.CenterStart
            ) {
                CompositionLocalProvider(LocalRippleTheme provides ChangedRippleThemeAlpha) {
                    Text(
                        modifier = Modifier.padding(start = 38.dp),
                        text = language.value,
                        style = MaterialTheme.typography.bodyLarge,
                        color = if (isPressed) PrimaryColor else Color.Black
                    )
                }
            }
        }
    }
}

private object ChangedRippleThemeAlpha : RippleTheme {
    @Composable
    override fun defaultColor(): Color = RippleColor

    @Composable
    override fun rippleAlpha(): RippleAlpha = RippleAlpha(0f, 0f, 0f, 0.5f)
}