package com.m4xvel.aitranslator.ui.screen.settingScreen.component

import androidx.compose.foundation.Indication
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.m4xvel.aitranslator.MainViewModel
import com.m4xvel.aitranslator.R
import com.m4xvel.aitranslator.ui.navigation.Screen
import com.m4xvel.aitranslator.ui.screen.util.customElement.SwitchButton

@Composable
fun SystemLanguageSelectionPanel(
    viewModel: MainViewModel,
    navController: NavController
) {

    val state by viewModel.state.collectAsState()

    Text(
        modifier = Modifier
            .padding(start = 20.dp, top = 23.dp),
        text = stringResource(id = R.string.language_text),
        color = MaterialTheme.colorScheme.onBackground,
        fontSize = 20.sp,
        fontWeight = FontWeight.Normal
    )

    LanguagePanel(
        paddingTop = 18,
        paddingTopStart = 5,
        paddingTopEnd = 5,
        paddingBottomStart = 0,
        paddingBottomEnd = 0,
        onClick = {

        },
        indication = null,
        enabled = false,
        content = {
            Text(
                text = stringResource(id = R.string.determine_automatically),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(start = 20.dp)
            )
            SwitchButton(
                isChecked = state.isChecked,
                onClick = {
                    viewModel.switch()
                }
            )
        }
    )

    LanguagePanel(
        paddingTop = 1,
        paddingTopStart = 0,
        paddingTopEnd = 0,
        paddingBottomStart = 5,
        paddingBottomEnd = 5,
        onClick = {
            navController.navigate(Screen.SYSTEM_LANGUAGE_SELECTION.name) {
                popUpTo(Screen.SETTINGS.name)
            }
        },
        indication = LocalIndication.current,
        enabled = state.isEnabled,
        content = {
            Text(
                text = "TEXT1",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(start = 20.dp)
            )
            Icon(
                imageVector = if (!state.isEnabled) Icons.Default.Lock else Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = null,
                modifier = Modifier
                    .padding(end = if (!state.isEnabled) 24.dp else 20.dp)
                    .size(size = if (!state.isEnabled) 18.dp else 24.dp),
                tint = MaterialTheme.colorScheme.onBackground
            )
        }
    )
}

@Composable
private fun LanguagePanel(
    paddingTop: Int,
    paddingTopStart: Int,
    paddingTopEnd: Int,
    paddingBottomStart: Int,
    paddingBottomEnd: Int,
    onClick: () -> Unit,
    indication: Indication?,
    enabled: Boolean,
    content: @Composable (RowScope.() -> Unit)
) {

    val interactionSource = remember { MutableInteractionSource() }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = paddingTop.dp)
            .clip(
                RoundedCornerShape(
                    topStart = paddingTopStart.dp,
                    topEnd = paddingTopEnd.dp,
                    bottomStart = paddingBottomStart.dp,
                    bottomEnd = paddingBottomEnd.dp
                )
            )
            .clickable(
                onClick = onClick,
                interactionSource = interactionSource,
                indication = indication,
                enabled = enabled
            )
            .height(41.dp)
            .background(MaterialTheme.colorScheme.surface),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        content = content
    )
}