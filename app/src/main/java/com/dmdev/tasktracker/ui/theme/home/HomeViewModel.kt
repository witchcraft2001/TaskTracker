package com.dmdev.tasktracker.ui.theme.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dmdev.tasktracker.data.ResultWrapper
import com.dmdev.tasktracker.usecases.GetAllTasksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val allTasksUseCase: GetAllTasksUseCase
): ViewModel() {
    private val _uiState = MutableStateFlow<HomeViewState>(HomeViewState.Loading)
    val uiState: StateFlow<HomeViewState> = _uiState

    init {
        loadTasks()
    }

    private fun loadTasks() {
        viewModelScope.launch {
            allTasksUseCase.getTasksWithCategories().collect { result ->
                when (result) {
                    is ResultWrapper.Success -> _uiState.value = HomeViewState.Success(result.result)
                    is ResultWrapper.Loading -> _uiState.value = HomeViewState.Loading
                    is ResultWrapper.Error -> _uiState.value = HomeViewState.Error(result.exception)
                }
            }
        }
    }

    fun reloadTasks() {
        loadTasks()
    }
}