package com.dmdev.tasktracker.ui.home

import androidx.lifecycle.viewModelScope
import com.dmdev.tasktracker.core.common.BaseViewModelEventHandler
import com.dmdev.tasktracker.core.common.UiState
import com.dmdev.tasktracker.data.ResultWrapper
import com.dmdev.tasktracker.ui.home.models.TaskListEvent
import com.dmdev.tasktracker.ui.home.models.TaskListViewState
import com.dmdev.tasktracker.ui.home.models.TaskModel
import com.dmdev.tasktracker.ui.home.models.TaskModelMapper
import com.dmdev.tasktracker.usecases.GetAllTasksUseCase
import com.dmdev.tasktracker.usecases.ToggleTaskUseCase
import com.dmdev.tasktracker.utils.TimeUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val allTasksUseCase: GetAllTasksUseCase,
    private val toggleTaskUseCase: ToggleTaskUseCase,
    private val timeUtils: TimeUtils
) : BaseViewModelEventHandler<TaskListViewState, TaskListEvent>(TaskListViewState.ListViewState()) {

    override fun obtainEvent(event: TaskListEvent) {
        when (val state = uiState.value) {
            is TaskListViewState.ErrorViewState -> reduce(event, state)
            is TaskListViewState.ListViewState -> reduce(event, state)
        }
    }

    private fun reduce(event: TaskListEvent, state: TaskListViewState.ErrorViewState) {
        when (event) {
            is TaskListEvent.ReloadEvent -> loadTasks()
        }
    }

    private fun reduce(event: TaskListEvent, state: TaskListViewState.ListViewState) {
        when (event) {
            is TaskListEvent.ReloadEvent -> loadTasks()
            is TaskListEvent.ToggleTaskEvent -> toggleTask(event.task)
        }
    }

    private fun loadTasks() {
        viewModelScope.launch {
            allTasksUseCase.execute().collect { result ->
                when (result) {
                    is ResultWrapper.Success -> _uiState.value =
                        TaskListViewState.ListViewState(result.result.map { TaskModelMapper.mapToModel(it, timeUtils) })
                    is ResultWrapper.Loading -> _uiState.value = TaskListViewState.ListViewState(isLoading = true)
                    is ResultWrapper.Error -> _uiState.value =
                        TaskListViewState.ErrorViewState(result.exception.message ?: "")
                }
            }
        }
    }

    private fun toggleTask(item: TaskModel) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _uiState.value = TaskListViewState.ListViewState(isLoading = true)
                toggleTaskUseCase.execute(item.id)
                obtainEvent(TaskListEvent.ReloadEvent)
            } catch (e: Throwable) {
                _uiState.value = TaskListViewState.ErrorViewState(e.message ?: "")
            }
        }
    }
}
