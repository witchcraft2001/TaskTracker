package com.dmdev.tasktracker.ui.theme.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.dmdev.tasktracker.navigation.NavState
import com.dmdev.tasktracker.ui.theme.BaseTheme

@Composable
fun Home(navState: NavState, vm: HomeViewModel) {
    Surface(modifier = Modifier.fillMaxSize(), color = BaseTheme.colors.background) {
        Column {
            Text(text = "Hello!")
            Button(onClick = {
                navState.navigateToTaskEdit()
            }) {
                Text(text = "Edit")
            }
        }
    }
}