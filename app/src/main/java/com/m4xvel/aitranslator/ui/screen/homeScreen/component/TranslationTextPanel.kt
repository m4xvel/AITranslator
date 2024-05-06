package com.m4xvel.aitranslator.ui.screen.homeScreen.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.m4xvel.aitranslator.R
import com.m4xvel.aitranslator.localDataState

@Composable
fun TranslationTextPanel() {

    val state = localDataState.current
    val clipboardManager: ClipboardManager = LocalClipboardManager.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .offset(y = (-60).dp)
            .height(IntrinsicSize.Min)
            .background(MaterialTheme.colorScheme.surface),
        content = {

            Box(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 25.dp, start = 20.dp, end = 15.dp)
                .verticalScroll(rememberScrollState()),
                content = {
                    Text(
                        text = state.transferText,
                        fontWeight = FontWeight.Normal,
                        fontSize = 20.sp,
                        color = MaterialTheme.colorScheme.onBackground
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
                            clipboardManager.setText(AnnotatedString(state.transferText))
                        },
                        content = {
                            Icon(
                                painter = painterResource(id = R.drawable.copy),
                                contentDescription = "delete",
                                tint = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    )
                }
            )
        }
    )
}