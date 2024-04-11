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
    startDestination: String = "${NavigationItem.Home.route}/{language}/{id}"
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(
            route = "${NavigationItem.Home.route}/{language}/{id}",
            arguments = listOf(
                navArgument("language") {
                    type = NavType.StringType
                },
                navArgument("id") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            HomeScreen(
                navController = navController,
                text = backStackEntry.arguments?.getString("language"),
                id = backStackEntry.arguments?.getInt("id")!!
            )
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
            )
        ) { backStackEntry ->
            LanguageSelectionScreen(
                navController = navController,
                text = backStackEntry.arguments?.getString("text")!!,
                id = backStackEntry.arguments?.getInt("id")!!
            )
        }
    }
}