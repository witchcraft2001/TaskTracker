package com.dmdev.tasktracker.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dmdev.tasktracker.data.ResultWrapper
import com.dmdev.tasktracker.usecases.GetAllTasksUseCase
import com.dmdev.tasktracker.utils.TimeUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val allTasksUseCase: GetAllTasksUseCase,
    private val timeUtils: TimeUtils
) : ViewModel() {
    private val _uiState = MutableStateFlow<HomeViewState>(HomeViewState.Loading)
    val uiState: StateFlow<HomeViewState> = _uiState

    init {
        loadTasks()
    }

    private fun loadTasks() {
        viewModelScope.launch {
            allTasksUseCase.getTasksWithCategoriesAndPeriods().collect { result ->
                when (result) {
                    is ResultWrapper.Success -> _uiState.value =
                        HomeViewState.Success(result.result.map { TaskModelMapper.mapToModel(it, timeUtils) })
                    is ResultWrapper.Loading -> _uiState.value = HomeViewState.Loading
                    is ResultWrapper.Error -> _uiState.value = HomeViewState.Error(result.exception)
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