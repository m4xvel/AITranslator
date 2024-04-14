package com.m4xvel.aitranslator.ui.screen.homeScreen.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.m4xvel.aitranslator.MainViewModel
import com.m4xvel.aitranslator.R
import com.m4xvel.aitranslator.ui.theme.LightSurface
import com.m4xvel.aitranslator.ui.theme.PrimaryColor

@Composable
fun CurrentTextPanel(
    viewModel: MainViewModel
) {

    val state by viewModel.state.collectAsState()
    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(state.inputText) {
        if (state.inputText.isEmpty()) {
            keyboardController?.show()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(185.dp)
            .clip(
                RoundedCornerShape(
                    bottomStart = if (state.showTranslationTextPanel) 0.dp else 10.dp,
                    bottomEnd = if (state.showTranslationTextPanel) 0.dp else 10.dp
                )
            )
            .background(LightSurface)
    ) {

        val fontSize = viewModel.decreaseFont()

        BasicTextField(
            singleLine = false,
            value = state.inputText,
            onValueChange = {
                viewModel.setInputText(it)
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(149.dp),
            textStyle = TextStyle(
                fontWeight = FontWeight.Normal,
                fontSize = fontSize.sp,
                color = Color.Black,
            ),
            decorationBox = { innerTextField ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp, start = 25.dp, end = 15.dp)
                ) {
                    if (state.inputText.isEmpty()) {

                        Text(
                            text = "${stringResource(id = R.string.enter_text)}...",
                            color = Color.Black,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Light,
                            modifier = Modifier.alpha(0.7f)
                        )
                    }
                    innerTextField()
                }
            }
        )
        BottomPanel(viewModel = viewModel)
    }
}

@Composable
private fun BottomPanel(viewModel: MainViewModel) {

    val clipboardManager: ClipboardManager = LocalClipboardManager.current

    Row(
        modifier = Modifier
            .fillMaxSize()
            .drawBehind {
                val strokeWidth = 1.dp.toPx()
                val y = size.height - strokeWidth / 2
                drawLine(
                    color = PrimaryColor,
                    start = Offset(0f, y),
                    end = Offset(size.width, y),
                    strokeWidth = strokeWidth
                )
            },
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.Bottom,
        content = {
            IconButton(
                onClick = { viewModel.deleteText() },
                content = {
                    Icon(
                        painter = painterResource(id = R.drawable.delete),
                        contentDescription = "delete",
                        tint = PrimaryColor
                    )
                }
            )
            IconButton(
                onClick = {
                    clipboardManager.getText()?.text?.let {
                        viewModel.pasteText(it)
                    }
                },
                content = {
                    Icon(
                        painter = painterResource(id = R.drawable.paste),
                        contentDescription = "paste",
                        tint = PrimaryColor
                    )
                }
            )
        }
    )
}