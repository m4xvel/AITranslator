package com.m4xvel.aitranslator.ui.screen.homeScreen.components

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
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.m4xvel.aitranslator.R
import com.m4xvel.aitranslator.ui.screen.homeScreen.HomeScreenViewModel
import com.m4xvel.aitranslator.ui.theme.SecondaryColor

@Composable
fun LanguageSelectionPanel(viewModel: HomeScreenViewModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(41.dp)
            .clip(RoundedCornerShape(topStart = 10.dp, topEnd = 20.dp))
            .background(color = SecondaryColor),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        CompositionLocalProvider(LocalRippleTheme provides ChangedRippleThemeAlpha) {
            LanguageItem(
                onClick = { },
                shapeTopStart = 10,
                shapeTopEnd = 0,
                text = viewModel.getCurrentLanguage()
            )
            IconButton(
                onClick = { /*TODO*/ }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.arrow_change),
                    contentDescription = "change language",
                    tint = Color.White
                )
            }
            LanguageItem(
                onClick = { },
                shapeTopStart = 0,
                shapeTopEnd = 10,
                text = stringResource(id = R.string.language_english)
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

private object ChangedRippleThemeAlpha : RippleTheme {
    @Composable
    override fun defaultColor(): Color = Color.White

    @Composable
    override fun rippleAlpha(): RippleAlpha = RippleAlpha(0f, 0f, 0f, 0.1f)
}