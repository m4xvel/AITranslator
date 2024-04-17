package com.m4xvel.aitranslator.ui.screen.homeScreen.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.m4xvel.aitranslator.MainViewModel
import com.m4xvel.aitranslator.R
import com.m4xvel.aitranslator.ui.theme.PrimaryColor

@Composable
fun InternetNoConnection(viewModel: MainViewModel) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 84.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(modifier = Modifier
            .width(250.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            content = {
                Text(
                    text = stringResource(id = R.string.no_connection),
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Normal
                )
                Text(
                    text = stringResource(id = R.string.no_connection_text),
                    modifier = Modifier.padding(top = 20.dp),
                    lineHeight = 19.sp,
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Light
                )
                Button(
                    onClick = {
                        viewModel.statusNetwork()
                    },
                    modifier = Modifier
                        .padding(top = 24.dp)
                        .wrapContentWidth()
                        .height(40.dp),
                    shape = RoundedCornerShape(10.dp),
                    contentPadding = PaddingValues(0.dp),
                    colors = ButtonColors(
                        containerColor = PrimaryColor,
                        contentColor = Color.White,
                        disabledContainerColor = PrimaryColor,
                        disabledContentColor = Color.White
                    ),
                    content = {
                        Text(
                            modifier = Modifier.padding(start = 12.dp, end = 12.dp),
                            text = stringResource(id = R.string.refresh_button),
                            style = MaterialTheme.typography.bodyLarge,
                            fontSize = 20.sp
                        )
                    }
                )
            }

        )
    }
}