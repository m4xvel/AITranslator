package com.m4xvel.aitranslator.ui.screen.languageSelectionScreen.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.m4xvel.aitranslator.MainViewModel
import com.m4xvel.aitranslator.R
import com.m4xvel.aitranslator.ui.screen.util.KeyboardListener
import com.m4xvel.aitranslator.ui.theme.PrimaryColor

@Composable
fun SearchLanguagePanel(
    viewModel: MainViewModel
) {

    KeyboardListener(viewModel)

    Box(
        modifier = Modifier
            .padding(start = 5.dp, end = 5.dp, top = 32.dp)
            .fillMaxWidth()
            .height(41.dp)
    ) {

        val state by viewModel.state.collectAsState()
        val enabled by remember { mutableStateOf(true) }
        val color = MaterialTheme.colorScheme.onSurface

        BasicTextField(
            singleLine = true,
            value = state.searchLanguage,
            onValueChange = {
                viewModel.setSearchLanguage(it)
            },
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(5.dp))
                .background(MaterialTheme.colorScheme.surface),
            textStyle = TextStyle(
                fontWeight = FontWeight.Normal,
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.onBackground
            ),
            decorationBox = { innerTextField ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .drawBehind {
                            val strokeWidth = 1.dp.toPx()
                            val y = size.height - strokeWidth / 2
                            drawLine(
                                color = color,
                                start = Offset(0f, y),
                                end = Offset(size.width, y),
                                strokeWidth = strokeWidth
                            )
                        },
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "search",
                        modifier = Modifier
                            .padding(start = 10.dp)
                            .size(32.dp),
                        tint = color
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 15.dp),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        if (state.searchLanguage.isEmpty()) {
                            Text(
                                text = stringResource(id = R.string.search),
                                color = MaterialTheme.colorScheme.onBackground,
                                fontSize = 20.sp,
                                modifier = Modifier.alpha(0.7f)
                            )
                        }
                        innerTextField()
                    }
                }
            },
            enabled = enabled,
            maxLines = 1,
            cursorBrush = if (!state.isKeyboardVisible) SolidColor(Color.Unspecified) else SolidColor(
                MaterialTheme.colorScheme.onBackground
            )
        )
    }
}