package com.m4xvel.aitranslator.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.m4xvel.aitranslator.MainViewModel
import com.m4xvel.aitranslator.ui.screen.homeScreen.HomeScreen
import com.m4xvel.aitranslator.ui.screen.languageSelectionScreen.LanguageSelectionScreen
import com.m4xvel.aitranslator.ui.screen.settingScreen.SettingScreen

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = NavigationItem.Home.route,
    viewModel: MainViewModel
) {

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination,
    ) {
        composable(
            route = NavigationItem.Home.route,
        ) {
            HomeScreen(
                navController = navController,
                viewModel = viewModel
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
                id = backStackEntry.arguments?.getInt("id")!!,
                viewModel = viewModel
            )
        }
        composable(
            route = NavigationItem.Settings.route
        ) {
            SettingScreen(
                viewModel = viewModel
            )
        }
    }
}