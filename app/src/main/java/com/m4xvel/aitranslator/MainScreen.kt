package com.m4xvel.aitranslator

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.m4xvel.aitranslator.ui.navigation.AppNavHost
import com.m4xvel.aitranslator.ui.navigation.BottomNavBar
import com.m4xvel.aitranslator.ui.navigation.NavigationItem
import com.m4xvel.aitranslator.ui.navigation.TopNavBar

@Composable
fun MainScreen() {

    val navController: NavHostController = rememberNavController()

    val viewmodel = localMainViewModel.current

    val state = localDataState.current

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Scaffold(
            topBar = {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route
                when (currentRoute) {
                    NavigationItem.Settings.route -> TopNavBar(text = stringResource(id = R.string.settings_text))
                    NavigationItem.SystemLanguageSelection.route -> TopNavBar(
                        text = stringResource(id = R.string.language_text),
                        icon = {
                            IconButton(onClick = {
                                navController.popBackStack()
                            }) {
                                Icon(
                                    modifier = Modifier.size(32.dp),
                                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                    tint = Color.White,
                                    contentDescription = "close"
                                )
                            }
                        }
                    )
                }
            },
            snackbarHost = {
                SnackbarHost(hostState = localDataState.current.snackbarHostState) {
                    Snackbar(
                        containerColor = MaterialTheme.colorScheme.secondary,
                        contentColor = Color.White,
                        shape = RoundedCornerShape(0),
                        action = {
                            TextButton(onClick = {
                                viewmodel.restartApp(state.currentSystemLanguage)
                            }) {
                                Text(
                                    text = stringResource(id = R.string.restart_button),
                                    fontSize = 16.sp,
                                    color = Color.White
                                )
                            }
                        }
                    ) {
                        Text(text = stringResource(id = R.string.restart_text))
                    }
                }
            },
            bottomBar = {
                BottomNavBar(navController = navController)
            },
            modifier = Modifier.fillMaxSize(),
            content = {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(it)
                ) {
                    AppNavHost(navController = navController)
                }
            }
        )
    }
}
