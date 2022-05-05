package com.dmdev.tasktracker.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dmdev.tasktracker.ui.theme.home.Home
import com.dmdev.tasktracker.ui.theme.task_edit.TaskEdit

@Composable
fun TaskTrackerNavigation(appState: NavState = rememberNavState()) {
    NavHost(
        navController = appState.navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) {
            Home(navState = appState)
        }
        composable(Screen.TaskEdit.route) {
            TaskEdit(navState = appState)
        }
    }
}

@Composable
fun rememberNavState(
    navController: NavHostController = rememberNavController(),
    context: Context = LocalContext.current
) = remember(navController, context) {
    NavState(navController)
}

class NavState(
    val navController: NavHostController
) {
    fun navigateBack() {
        navController.popBackStack()
    }

    fun navigateToTaskEdit() {
        navController.navigate(Screen.TaskEdit.route)
    }

    fun navigateToHome() {
        navController.navigate(Screen.Home.route)
    }
}

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object TaskEdit : Screen("tasks/edit")
}