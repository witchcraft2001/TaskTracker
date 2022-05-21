package com.dmdev.tasktracker.ui.drawer

import com.dmdev.tasktracker.navigation.Screen

sealed class DrawerScreens(val title: String, val route: String) {
    object Tasks : DrawerScreens("Home", Screen.Home.route)
    object Reports : DrawerScreens("Reports", Screen.Reports.route)
}