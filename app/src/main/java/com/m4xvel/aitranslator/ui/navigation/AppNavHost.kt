package com.m4xvel.aitranslator.ui.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument
import com.m4xvel.aitranslator.ui.screen.homeScreen.HomeScreen
import com.m4xvel.aitranslator.ui.screen.languageSelectionScreen.LanguageSelectionScreen
import com.m4xvel.aitranslator.ui.screen.settingScreen.SettingScreen
import com.m4xvel.aitranslator.ui.screen.systemLanguageSelectionScreen.SystemLanguageSelectionScreen
import com.m4xvel.aitranslator.ui.screen.util.StatusBarColor

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = NavigationItem.Home.route,
) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    when (currentRoute) {
        NavigationItem.Home.route -> StatusBarColor(MaterialTheme.colorScheme.background)
        else -> StatusBarColor(MaterialTheme.colorScheme.surfaceContainerHigh)
    }

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {

        composable(
            route = NavigationItem.Home.route,
            enterTransition = {
                when (initialState.destination.route) {
                    NavigationItem.Settings.route -> slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.End,
                        animationSpec = tween(200)
                    )

                    "${NavigationItem.LanguageSelection.route}/{text}/{id}" -> slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Down,
                        animationSpec = tween(400)
                    )

                    NavigationItem.SystemLanguageSelection.route -> slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.End,
                        animationSpec = tween(200)
                    )

                    else -> EnterTransition.None
                }
            },
            exitTransition = {
                when (initialState.destination.route) {
                    NavigationItem.Settings.route -> slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Start,
                        animationSpec = tween(200)
                    )

                    NavigationItem.Home.route -> slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Start,
                        animationSpec = tween(200)
                    )

                    else -> ExitTransition.None
                }
            }
        ) {
            HomeScreen(navController = navController)
        }
        composable(
            route = "${NavigationItem.LanguageSelection.route}/{text}/{id}",
            arguments = listOf(
                navArgument("text") {
                    type = NavType.StringType
                },
                navArgument("id") {
                    type = NavType.IntType
                }
            ),
            enterTransition = {
                when (initialState.destination.route) {
                    NavigationItem.Home.route -> slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Up,
                        animationSpec = tween(600)
                    )

                    else -> EnterTransition.None
                }
            },
            exitTransition = {
                when (initialState.destination.route) {
                    "${NavigationItem.LanguageSelection.route}/{text}/{id}" -> slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Down,
                        animationSpec = tween(200)
                    )

                    NavigationItem.Home.route -> slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Up,
                        animationSpec = tween(600)
                    )

                    else -> ExitTransition.None
                }
            }
        ) { backStackEntry ->

            LanguageSelectionScreen(
                navController = navController,
                text = backStackEntry.arguments?.getString("text")!!,
                id = backStackEntry.arguments?.getInt("id")!!
            )
        }
        composable(
            route = NavigationItem.Settings.route,
            enterTransition = {
                when (initialState.destination.route) {
                    NavigationItem.Home.route -> slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Start,
                        animationSpec = tween(200)
                    )

                    NavigationItem.SystemLanguageSelection.route -> slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.End,
                        animationSpec = tween(200)
                    )

                    else -> EnterTransition.None
                }
            },
            exitTransition = {
                ExitTransition.None
            }
        ) {
            SettingScreen(navController = navController)
        }
        composable(
            route = NavigationItem.SystemLanguageSelection.route,
            enterTransition = {
                when (initialState.destination.route) {
                    NavigationItem.Settings.route -> slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Start,
                        animationSpec = tween(200)
                    )

                    else -> EnterTransition.None
                }
            },
            exitTransition = {
                ExitTransition.None
            }
        ) {
            SystemLanguageSelectionScreen()
        }
    }
}