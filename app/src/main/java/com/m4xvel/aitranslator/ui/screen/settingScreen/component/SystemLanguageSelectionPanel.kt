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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.m4xvel.aitranslator.MainViewModel
import com.m4xvel.aitranslator.ui.screen.util.customElement.SwitchButton

@Composable
fun SystemLanguageSelectionPanel(viewModel: MainViewModel) {

    val state by viewModel.state.collectAsState()

    Text(
        modifier = Modifier
            .padding(start = 20.dp, top = 23.dp),
        text = "Язык",
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
        content = {
            Text(
                text = "Определять автоматически",
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

        },
        indication = LocalIndication.current,
        content = {
            Text(
                text = "Русский",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(start = 20.dp)
            )
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = null,
                modifier = Modifier
                    .padding(end = 18.dp)
                    .size(24.dp),
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
                indication = indication
            )
            .height(41.dp)
            .background(MaterialTheme.colorScheme.surface),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        content = content
    )
}