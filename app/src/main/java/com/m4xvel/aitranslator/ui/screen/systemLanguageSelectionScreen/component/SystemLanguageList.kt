package com.m4xvel.aitranslator.ui.screen.systemLanguageSelectionScreen.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.m4xvel.aitranslator.MainViewModel

@Composable
fun SystemLanguageList(
    viewModel: MainViewModel
) {

    val state by viewModel.state.collectAsState()

    val language = viewModel.getAllLanguages()

    val languageFilter = language.filterValues { it.contains(state.searchLanguage) }

    val firstLanguage = language.values.toList().first()

    val lastLanguage = language.values.toList().last()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 32.dp)
            .verticalScroll(rememberScrollState())
    ) {
        languageFilter.forEach { language ->
            Box(
                modifier = Modifier
                    .padding(
                        bottom = 1.dp,
                        start = 15.dp,
                        end = 15.dp
                    )
                    .fillMaxWidth()
                    .height(41.dp)
                    .clip(
                        RoundedCornerShape(
                            topStart = if (language.value == firstLanguage) 5.dp else 0.dp,
                            topEnd = if (language.value == firstLanguage) 5.dp else 0.dp,
                            bottomStart = if (language.value == lastLanguage) 5.dp else 0.dp,
                            bottomEnd = if (language.value == lastLanguage) 5.dp else 0.dp
                        )
                    )
                    .background(MaterialTheme.colorScheme.surface)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        modifier = Modifier
                            .padding(start = 20.dp),
                        text = language.value,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                        contentDescription = null,
                        modifier = Modifier
                            .padding(end = 18.dp)
                            .size(size = 24.dp),
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                }
            }
        }
    }

}