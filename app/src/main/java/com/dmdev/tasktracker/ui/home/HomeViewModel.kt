package com.dmdev.tasktracker.ui.home

import androidx.lifecycle.viewModelScope
import com.dmdev.tasktracker.core.common.BaseViewModel
import com.dmdev.tasktracker.core.common.UiState
import com.dmdev.tasktracker.data.ResultWrapper
import com.dmdev.tasktracker.usecases.GetAllTasksUseCase
import com.dmdev.tasktracker.utils.TimeUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val allTasksUseCase: GetAllTasksUseCase,
    private val timeUtils: TimeUtils
) : BaseViewModel<UiState<List<TaskModel>>>(UiState.Loading()) {

    private fun loadTasks() {
        viewModelScope.launch {
            allTasksUseCase.getTasksWithCategoriesAndPeriods().collect { result ->
                when (result) {
                    is ResultWrapper.Success -> _uiState.value =
                        UiState.Success(result.result.map { TaskModelMapper.mapToModel(it, timeUtils) })
                    is ResultWrapper.Loading -> _uiState.value = UiState.Loading()
                    is ResultWrapper.Error -> _uiState.value = if (result.exception.message.isNullOrEmpty()) {
                        UiState.NetworkError()
                    } else {
                        UiState.Error(result.exception.message ?: "")
                    }
                }
            }
        }
    }

    fun reloadTasks() {
        loadTasks()
    }

    fun toggleTask(item: TaskModel) {

    }
}