package com.m4xvel.aitranslator.ui.screen.settingScreen.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.m4xvel.aitranslator.MainViewModel
import com.m4xvel.aitranslator.R
import com.m4xvel.aitranslator.ui.screen.util.ChangedRippleThemeAlpha
import com.m4xvel.aitranslator.ui.theme.AppTheme

@Composable
fun ThemeSelectionPanel(
    viewModel: MainViewModel
) {

    val state by viewModel.state.collectAsState()

    Text(
        modifier = Modifier
            .padding(start = 20.dp),
        text = stringResource(id = R.string.theme_design),
        color = MaterialTheme.colorScheme.onBackground,
        fontSize = 20.sp,
        fontWeight = FontWeight.Normal
    )
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 18.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        val systemContainerColor =
            if (state.theme == AppTheme.Default) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface

        val systemContentColor =
            if (state.theme == AppTheme.Default) Color.White else MaterialTheme.colorScheme.onBackground

        val lightContainerColor =
            if (state.theme == AppTheme.Light) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface

        val lightContentColor =
            if (state.theme == AppTheme.Light) Color.White else MaterialTheme.colorScheme.onBackground

        val darkContainerColor =
            if (state.theme == AppTheme.Dark) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface

        val darkContentColor =
            if (state.theme == AppTheme.Dark) Color.White else MaterialTheme.colorScheme.onBackground

        CompositionLocalProvider(
            LocalRippleTheme provides ChangedRippleThemeAlpha(
                color = Color.Unspecified,
                rippleAlpha = RippleAlpha(0f, 0f, 0f, 0f)
            )
        ) {

            SystemTheme(
                textColor = systemContentColor,
                buttonColor = systemContainerColor,
                onClick = {
                    viewModel.setTheme(0)
                })
            AnotherTheme(
                onClick = {
                    viewModel.setTheme(1)
                },
                buttonColor = lightContainerColor,
                content = {
                    Icon(
                        painter = painterResource(id = R.drawable.sunny),
                        tint = lightContentColor,
                        contentDescription = null
                    )
                    Text(
                        modifier = Modifier
                            .padding(top = 16.dp),
                        text = stringResource(id = R.string.light),
                        color = lightContentColor,
                        textAlign = TextAlign.Center
                    )
                }
            )
            AnotherTheme(
                onClick = {
                    viewModel.setTheme(2)
                },
                buttonColor = darkContainerColor,
                content = {
                    Icon(
                        painter = painterResource(id = R.drawable.moon),
                        tint = darkContentColor,
                        contentDescription = null
                    )
                    Text(
                        modifier = Modifier
                            .padding(top = 16.dp),
                        text = stringResource(id = R.string.dark),
                        color = darkContentColor,
                        textAlign = TextAlign.Center
                    )
                }
            )
        }
    }
}

@Composable
private fun SystemTheme(
    textColor: Color,
    buttonColor: Color,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .width(140.dp)
            .height(100.dp)
            .clip(shape = RoundedCornerShape(5.dp))
            .background(buttonColor)
            .clickable(onClick = onClick),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.system),
            color = textColor,
            textAlign = TextAlign.Center
        )
        Text(
            modifier = Modifier
                .padding(top = 4.dp),
            text = stringResource(id = R.string.same_device_text),
            color = textColor,
            textAlign = TextAlign.Center,
            fontSize = 14.sp,
            fontWeight = FontWeight.Light
        )
    }
}

@Composable
private fun AnotherTheme(
    onClick: () -> Unit,
    buttonColor: Color,
    content: @Composable (ColumnScope.() -> Unit)
) {
    Column(
        modifier = Modifier
            .width(80.dp)
            .height(100.dp)
            .clip(shape = RoundedCornerShape(5.dp))
            .background(buttonColor)
            .clickable(
                onClick = onClick
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        content = content
    )
}