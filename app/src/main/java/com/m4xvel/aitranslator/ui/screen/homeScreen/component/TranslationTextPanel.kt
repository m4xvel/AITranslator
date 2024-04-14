package com.m4xvel.aitranslator.ui.screen.homeScreen.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.m4xvel.aitranslator.MainViewModel
import com.m4xvel.aitranslator.R
import com.m4xvel.aitranslator.ui.theme.LightSurface
import com.m4xvel.aitranslator.ui.theme.PrimaryColor

@Composable
fun TranslationTextPanel(
    viewModel: MainViewModel
) {

    val state by viewModel.state.collectAsState()
    val clipboardManager: ClipboardManager = LocalClipboardManager.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .offset(y = (-60).dp)
            .height(185.dp)
            .clip(
                RoundedCornerShape(
                    topStart = 10.dp,
                    topEnd = 10.dp
                )
            )
            .background(LightSurface),
        content = {

            Box(modifier = Modifier
                .fillMaxWidth()
                .height(149.dp)
                .padding(top = 25.dp, start = 20.dp, end = 15.dp)
                .verticalScroll(rememberScrollState()),
                content = {
                    Text(
                        text = state.transferText.toString(),
                        fontWeight = FontWeight.Normal,
                        fontSize = 20.sp
                    )
                }
            )
            Row(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.Bottom,
                content = {
                    IconButton(
                        onClick = {
                            clipboardManager.setText(AnnotatedString(state.transferText.toString()))
                        },
                        content = {
                            Icon(
                                painter = painterResource(id = R.drawable.copy),
                                contentDescription = "delete",
                                tint = PrimaryColor
                            )
                        }
                    )
                }
            )
        }
    )
}