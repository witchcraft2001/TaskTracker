package com.dmdev.tasktracker.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.dmdev.tasktracker.core.extensions.getOrElse
import com.dmdev.tasktracker.ui.category_chooser.CategoryChooser
import com.dmdev.tasktracker.ui.home.Home
import com.dmdev.tasktracker.ui.task_edit.TaskEdit

@Composable
fun TaskTrackerNavigation(appState: NavState = rememberNavState()) {
    NavHost(
        navController = appState.navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) {
            Home(navState = appState, hiltViewModel())
        }
        composable(Screen.TaskEdit.route) {
            TaskEdit(navState = appState, hiltViewModel())
        }
        composable(Screen.CategoryChooser.route, arguments = listOf(navArgument("categoryId") { nullable = true })) {
            TaskEdit(navState = appState, hiltViewModel())
            CategoryChooser(
                navState = appState,
                it.arguments?.getString("categoryId")?.toLongOrNull(),
                hiltViewModel()
            )
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

    fun navigateToCategoryChooser(categoryId: Long?) {
        navController.navigate(Screen.CategoryChooser.createRoute(categoryId))
    }

    fun navigateToHome() {
        navController.navigate(Screen.Home.route)
    }
}

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object TaskEdit : Screen("tasks/edit")
    object CategoryChooser : Screen("categories?categoryId={categoryId}") {
        fun createRoute(categoryId: Long?) = route.replace("{categoryId}", categoryId?.toString().getOrElse(""))
    }
}