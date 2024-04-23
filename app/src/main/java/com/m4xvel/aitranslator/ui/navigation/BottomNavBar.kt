package com.m4xvel.aitranslator.ui.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import com.m4xvel.aitranslator.R

@Composable
fun BottomNavBar(
    navController: NavController
) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val interactionSource = remember { MutableInteractionSource() }

    if (currentDestination?.hierarchy?.any { it.route == "${Screen.LANGUAGE_SELECTION.name}/{text}/{id}" } == false) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .background(MaterialTheme.colorScheme.surface),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Icon(
                modifier = Modifier.clickable(
                    onClick = {
                        navController.navigate(Screen.HOME.name)
                    },
                    enabled = !currentDestination.hierarchy.any { it.route == Screen.HOME.name },
                    interactionSource = interactionSource,
                    indication = null
                ),
                painter = if (currentDestination.hierarchy.any { it.route == Screen.HOME.name }) {
                    painterResource(id = R.drawable.home)
                } else {
                    painterResource(id = R.drawable.home_uncolor)
                },
                contentDescription = "Home",
                tint = MaterialTheme.colorScheme.onSurface
            )

            Icon(
                modifier = Modifier.clickable(
                    onClick = {
                        navController.navigate(Screen.SETTINGS.name)
                    },
                    enabled = !currentDestination.hierarchy.any { it.route == Screen.SETTINGS.name },
                    interactionSource = interactionSource,
                    indication = null
                ),
                painter = if (currentDestination.hierarchy.any { it.route == Screen.SETTINGS.name }) {
                    painterResource(id = R.drawable.settings)
                } else {
                    painterResource(id = R.drawable.settings_uncolor)
                },
                contentDescription = "Settings",
                tint = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}