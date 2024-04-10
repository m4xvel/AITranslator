package com.m4xvel.aitranslator.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.m4xvel.aitranslator.ui.screen.homeScreen.HomeScreen
import com.m4xvel.aitranslator.ui.screen.languageSelectionScreen.LanguageSelectionScreen

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = NavigationItem.Home.route
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(NavigationItem.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(
            "${NavigationItem.LanguageSelection.route}/{text}",
            arguments = listOf(navArgument("text") {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            LanguageSelectionScreen(
                navController = navController,
                text = backStackEntry.arguments?.getString("text")!!
            )
        }
    }
}