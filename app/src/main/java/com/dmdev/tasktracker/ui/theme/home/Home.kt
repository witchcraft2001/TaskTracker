package com.dmdev.tasktracker.ui.theme.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.dmdev.tasktracker.R
import com.dmdev.tasktracker.data.domain.Task
import com.dmdev.tasktracker.navigation.NavState
import com.dmdev.tasktracker.ui.theme.*

@Composable
fun Home(navState: NavState, vm: HomeViewModel) {
    val state = vm.uiState.collectAsState()
    Surface(modifier = Modifier.fillMaxSize(), color = BaseTheme.colors.background) {
        Column {
            ToolbarTextWithActionButton(
                title = stringResource(R.string.app_name),
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
            when (val value = state.value) {
                is HomeViewState.Loading -> {
                    LoadingBox()
                }
                is HomeViewState.Success -> {
                    TaskList(value.items) {
                        navState.navigateToTaskEdit()
                    }
                }
                is HomeViewState.Error -> {
                    ErrorBox(
                        message = value.exception.message ?: stringResource(R.string.text_something_was_wrong),
                        buttonText = stringResource(R.string.button_reload)
                    ) {
                        vm.reloadTasks()
                    }
                }
            }
        }
    }
}

@Composable
fun TaskList(
    items: List<Task>,
    onItemClicked: (Task?) -> Unit
) {
    Column(modifier = Modifier.padding(16.dp)) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(0.dp),
            modifier = Modifier
                .weight(1f)
        ) {
            items(items.count()) { index ->
                Text(
                    items[index].name,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
        ButtonTextCenter(text = "Добавить задачу") { onItemClicked(null) }
    }
}
