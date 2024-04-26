package com.m4xvel.aitranslator.ui.navigation

enum class Screen {
    HOME,
    LANGUAGE_SELECTION,
    SETTINGS,
    SYSTEM_LANGUAGE_SELECTION
}

sealed class NavigationItem(val route: String) {
    data object Home: NavigationItem(Screen.HOME.name)
    data object LanguageSelection: NavigationItem(Screen.LANGUAGE_SELECTION.name)
    data object Settings: NavigationItem(Screen.SETTINGS.name)
    data object SystemLanguageSelection: NavigationItem(Screen.SYSTEM_LANGUAGE_SELECTION.name)
}