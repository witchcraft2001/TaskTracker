package com.dmdev.tasktracker.ui.reports

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.dmdev.tasktracker.R
import com.dmdev.tasktracker.core.extensions.getOrElse
import com.dmdev.tasktracker.data.domain.ReportPeriod
import com.dmdev.tasktracker.navigation.NavState
import com.dmdev.tasktracker.ui.ErrorBox
import com.dmdev.tasktracker.ui.LoadingBox
import com.dmdev.tasktracker.ui.ToolbarTextWithActionButton
import com.dmdev.tasktracker.ui.common.DropdownList
import com.dmdev.tasktracker.ui.home.TaskList
import com.dmdev.tasktracker.ui.home.models.TaskListEvent
import com.dmdev.tasktracker.ui.home.models.TaskListViewState
import com.dmdev.tasktracker.ui.reports.models.ReportsEvent
import com.dmdev.tasktracker.ui.reports.models.ReportsViewState
import com.dmdev.tasktracker.ui.theme.BaseTheme

@Composable
fun Reports(
    navState: NavState,
    openDrawer: () -> Unit,
    vm: ReportsViewModel
) {
    Column {
        ToolbarTextWithActionButton(
            title = stringResource(R.string.text_reports),
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
            }
        )
        when (val state = vm.uiState.collectAsState().value) {
            is ReportsViewState.ShowReportViewState -> {
                if (state.isLoading) {
                    LoadingBox()
                } else {
                    ReportShow(
                        state = state,
                        onPeriodChanged = { vm.obtainEvent(ReportsEvent.SelectedPeriodEvent(it)) })
                }
            }
            is ReportsViewState.ErrorReportViewState -> {
                ErrorBox(
                    message = state.message.getOrElse(stringResource(R.string.text_something_was_wrong)),
                    buttonText = stringResource(R.string.button_reload)
                ) {
                    vm.obtainEvent(ReportsEvent.ReloadEvent)
                }
            }
        }
    }
}

@Composable
fun ReportShow(
    state: ReportsViewState.ShowReportViewState,
    onPeriodChanged: (ReportPeriod) -> Unit
) {
    Column(modifier = Modifier.padding(16.dp)) {
        DropdownList(
            modifier = Modifier.padding(top = 16.dp),
            items = ReportPeriod.values().map { stringResource(it.id) }.toList(),
            selectedItem = state.period,
            label = stringResource(R.string.text_period),
            hint = stringResource(R.string.hint_choose_period),
            onSelectedIndexChanged = { index ->
                onPeriodChanged(ReportPeriod.values()[index])
            }
        )

        Text(
            modifier = Modifier
                .padding(top = 8.dp)
                .fillMaxWidth(),
            text = state.dateRangeString,
            style = BaseTheme.typography.text14R
        )

        state.circleData?.let { data ->
            Box(Modifier.padding(16.dp)) {
                AnimatedCircle(
                    data.proportions,
                    colors = data.colors,
                    Modifier
                        .height(300.dp)
                        .align(Alignment.Center)
                        .fillMaxWidth()
                )
            }
        }
    }
}