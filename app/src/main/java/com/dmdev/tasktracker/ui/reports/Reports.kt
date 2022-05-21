package com.dmdev.tasktracker.ui.reports

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.dmdev.tasktracker.R
import com.dmdev.tasktracker.navigation.NavState
import com.dmdev.tasktracker.ui.ToolbarTextWithActionButton

@Composable
fun Reports(
    navState: NavState,
    openDrawer: () -> Unit
) {
    Column {
        ToolbarTextWithActionButton(
            title = stringResource(R.string.app_name),
            menuIcon = {
                IconButton(
                    onClick = { openDrawer() },
                    modifier = Modifier
                        .size(24.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_burger),
                        contentDescription = stringResource(R.string.hint_menu),
                        modifier = Modifier.size(24.dp)
                    )
                }
            },
            actionIcon = {
                IconButton(
                    onClick = { navState.navigateToTaskEdit() },
                    modifier = Modifier
                        .size(24.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_add),
                        contentDescription = stringResource(R.string.hint_add_task),
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        )
        Text("Reports")
    }
}