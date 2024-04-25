package com.m4xvel.aitranslator.ui.screen.util.customElement

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
fun SwitchButton(
    isChecked: Boolean,
    onClick: () -> Unit
) {

    val interactionSource = remember { MutableInteractionSource() }

    Box(
        modifier = Modifier
            .padding(end = 18.dp)
            .size(30.dp, 15.dp)
            .clip(RoundedCornerShape(15.dp))
            .background(
                if (!isChecked) {
                    MaterialTheme.colorScheme.onBackground
                } else {
                    MaterialTheme.colorScheme.secondary
                }
            )
            .clickable(
                onClick = onClick,
                interactionSource = interactionSource,
                indication = null
            ),
        contentAlignment = if (!isChecked) Alignment.CenterStart else Alignment.CenterEnd
    ) {
        if (!isChecked) {
            Box(
                modifier = Modifier
                    .padding(start = 2.dp)
                    .size(9.dp)
                    .background(MaterialTheme.colorScheme.surface, CircleShape)
            )
        } else {
            Box(
                modifier = Modifier
                    .padding(end = 2.dp)
                    .size(9.dp)
                    .background(MaterialTheme.colorScheme.surface, CircleShape)
            )
        }
    }
}