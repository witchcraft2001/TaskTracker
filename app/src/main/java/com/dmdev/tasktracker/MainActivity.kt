package com.dmdev.tasktracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.dmdev.tasktracker.navigation.Screen
import com.dmdev.tasktracker.navigation.TaskTrackerNavigation
import com.dmdev.tasktracker.navigation.rememberNavState
import com.dmdev.tasktracker.ui.drawer.Drawer
import com.dmdev.tasktracker.ui.theme.BaseTheme
import com.dmdev.tasktracker.ui.theme.TaskTrackerTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val drawerState = rememberDrawerState(DrawerValue.Closed)
            val scope = rememberCoroutineScope()
            val openDrawer = {
                scope.launch {
                    drawerState.open()
                }
            }
//            val navController = rememberNavController()
            val appState = rememberNavState()
            TaskTrackerTheme {
                ModalDrawer(
                    drawerState = drawerState,
                    gesturesEnabled = drawerState.isOpen,
                    drawerContent = {
                        Drawer(
                            onDestinationClicked = { route ->
                                scope.launch {
                                    drawerState.close()
                                }
                                appState.navController.navigate(route) {
                                    //Clear backstack
                                    popUpTo(0) {
                                        inclusive = true
                                    }
                                    launchSingleTop = true
                                }
                            }
                        )
                    }
                ) {
                    TaskTrackerNavigation(appState) {
                        openDrawer()
                    }
                }
            }
        }
    }
}