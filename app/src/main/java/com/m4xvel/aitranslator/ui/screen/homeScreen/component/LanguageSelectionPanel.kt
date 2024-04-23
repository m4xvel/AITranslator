package com.m4xvel.aitranslator.ui.screen.homeScreen.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.m4xvel.aitranslator.MainViewModel
import com.m4xvel.aitranslator.R
import com.m4xvel.aitranslator.ui.navigation.Screen
import com.m4xvel.aitranslator.ui.screen.util.ChangedRippleThemeAlpha

@Composable
fun LanguageSelectionPanel(
    navController: NavController,
    viewModel: MainViewModel
) {

    val state by viewModel.state.collectAsState()

    val languageFrom = stringResource(id = R.string.translate_from)
    val languageTo = stringResource(id = R.string.translate_to)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(41.dp)
            .clip(RoundedCornerShape(topStart = 10.dp, topEnd = 20.dp))
            .background(color = MaterialTheme.colorScheme.secondary),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        CompositionLocalProvider(
            LocalRippleTheme provides ChangedRippleThemeAlpha(
                color = Color.White,
                rippleAlpha = RippleAlpha(0f, 0f, 0f, 0.1f)
            )
        ) {
            LanguageItem(
                onClick = {
                    navController.navigate(
                        route = "${Screen.LANGUAGE_SELECTION.name}/$languageFrom/${state.leftButtonID}"
                    )
                },
                shapeTopStart = 10,
                shapeTopEnd = 0,
                text = state.currentLanguage.toString()
            )
            IconButton(
                onClick = { viewModel.swapLanguage() }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.arrow_change),
                    contentDescription = "change language",
                    tint = Color.White
                )
            }
            LanguageItem(
                onClick = {
                    navController.navigate(
                        route = "${Screen.LANGUAGE_SELECTION.name}/$languageTo/${state.rightButtonID}"
                    )
                },
                shapeTopStart = 0,
                shapeTopEnd = 10,
                text = state.translationLanguage.toString()
            )
        }
    }
}

@Composable
private fun LanguageItem(onClick: () -> Unit, shapeTopStart: Int, shapeTopEnd: Int, text: String) {
    Box(
        modifier = Modifier.width(170.dp),
        contentAlignment = Alignment.Center
    ) {
        TextButton(
            onClick = onClick,
            modifier = Modifier.fillMaxSize(),
            shape = RoundedCornerShape(
                topStart = shapeTopStart.dp,
                topEnd = shapeTopEnd.dp,
                bottomStart = 0.dp,
                bottomEnd = 0.dp
            ),
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 0.dp,
                pressedElevation = 0.dp
            )
        ) {
            Text(
                text = text,
                color = Color.White,
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 20.sp
            )
        }
    }
}