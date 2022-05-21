package com.dmdev.tasktracker.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.dmdev.tasktracker.R
import com.dmdev.tasktracker.core.extensions.getOrElse
import com.dmdev.tasktracker.navigation.NavState
import com.dmdev.tasktracker.ui.ButtonTextCenter
import com.dmdev.tasktracker.ui.ErrorBox
import com.dmdev.tasktracker.ui.LoadingBox
import com.dmdev.tasktracker.ui.ToolbarTextWithActionButton
import com.dmdev.tasktracker.ui.home.models.TaskListEvent
import com.dmdev.tasktracker.ui.home.models.TaskListViewState
import com.dmdev.tasktracker.ui.home.models.TaskModel
import com.dmdev.tasktracker.ui.theme.BaseTheme

@Composable
fun Home(
    navState: NavState,
    vm: HomeViewModel,
    openDrawer: () -> Unit
) {
    LaunchedEffect(Unit) {
        vm.obtainEvent(TaskListEvent.ReloadEvent)
    }

    Surface(modifier = Modifier.fillMaxSize(), color = BaseTheme.colors.background) {
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
            when (val state = vm.uiState.collectAsState().value) {
                is TaskListViewState.ListViewState -> {
                    if (state.isLoading) {
                        LoadingBox()
                    } else {
                        TaskList(
                            state.items,
                            onItemClicked = { navState.navigateToTaskEdit() },
                            onControlButtonClicked = { vm.obtainEvent(TaskListEvent.ToggleTaskEvent(it)) })
                    }
                }
                is TaskListViewState.ErrorViewState -> {
                    ErrorBox(
                        message = state.message.getOrElse(stringResource(R.string.text_something_was_wrong)),
                        buttonText = stringResource(R.string.button_reload)
                    ) {
                        vm.obtainEvent(TaskListEvent.ReloadEvent)
                    }
                }
            }
        }
    }
}

@Composable
fun TaskList(
    items: List<TaskModel>,
    onItemClicked: (TaskModel?) -> Unit,
    onControlButtonClicked: (TaskModel) -> Unit
) {
    Column {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(0.dp),
            modifier = Modifier
                .weight(1f)
        ) {
            items(items.count()) { index ->
                TaskItem(items[index], onItemClicked, onControlButtonClicked)
                if (index < items.count() - 1) {
                    Divider(
                        color = BaseTheme.colors.bgGray,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                }
            }
        }
        ButtonTextCenter(
            text = stringResource(R.string.button_add_task),
            modifier = Modifier.padding(16.dp)
        ) { onItemClicked(null) }
    }
}


@Composable
fun TaskItem(
    item: TaskModel,
    onItemClicked: (TaskModel) -> Unit,
    onControlButtonClicked: (TaskModel) -> Unit
) {
    Row(
        modifier = Modifier
            .height(64.dp)
            .clickable { onItemClicked(item) }
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(item.category.color.value)
        ) {
            Icon(
                painter = painterResource(id = item.category.icon.resourceId),
                contentDescription = item.category.name,
                tint = BaseTheme.colors.textWhite,
                modifier = Modifier
                    .size(24.dp)
                    .align(Alignment.Center)
            )
        }
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = item.name,
                style = BaseTheme.typography.text15B,
                color = BaseTheme.colors.textBlack,
                maxLines = 1,
                overflow = TextOverflow.Clip,
                modifier = Modifier.padding(start = 16.dp, end = 16.dp)
            )
            Row(modifier = Modifier.padding(top = 4.dp)) {
                Text(
                    text = item.category.name,
                    style = BaseTheme.typography.text12R,
                    color = BaseTheme.colors.textGray,
                    maxLines = 1,
                    overflow = TextOverflow.Clip,
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp)
                        .weight(1f)
                )
                Icon(
                    painter = painterResource(id = R.drawable.ic_timer),
                    contentDescription = stringResource(R.string.hint_elapsed_time),
                    tint = BaseTheme.colors.textGray,
                    modifier = Modifier.size(16.dp)
                )
                Text(
                    text = item.elapsedTime,
                    style = BaseTheme.typography.text12R,
                    color = BaseTheme.colors.textGray,
                    maxLines = 1,
                    overflow = TextOverflow.Clip,
                    modifier = Modifier.padding(start = 4.dp, end = 16.dp)
                )
            }
        }
        IconButton(
            onClick = { onControlButtonClicked(item) },
            modifier = Modifier
                .padding(8.dp)
                .size(24.dp)
        ) {
            Icon(
                painter = painterResource(
                    id = if (item.endedAt == null) {
                        R.drawable.ic_pause
                    } else {
                        R.drawable.ic_play
                    }
                ),
                contentDescription = stringResource(
                    id = if (item.endedAt == null) {
                        R.string.hint_pause_task
                    } else {
                        R.string.hint_start_task
                    }
                ),
                modifier = Modifier.size(24.dp)
            )
        }
    }
}