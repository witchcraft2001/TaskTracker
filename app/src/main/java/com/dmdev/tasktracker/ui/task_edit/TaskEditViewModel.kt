package com.dmdev.tasktracker.ui.task_edit

import com.dmdev.tasktracker.core.common.BaseViewModel
import com.dmdev.tasktracker.core.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class TaskEditViewModel @Inject constructor() : BaseViewModel<UiState<TaskEditViewState>>(UiState.Loading()) {
    private val _viewState = MutableStateFlow(TaskEditViewState())
    val viewState: StateFlow<TaskEditViewState> = _viewState
    val id: Long? = null

    init {
        _uiState.value = UiState.Success(_viewState.value)
    }

    fun save() {

    }

    fun onNameUpdated(name: String) {
        _viewState.value = viewState.value.copy(name = name, nameError = name.isEmpty())
        _uiState.value = UiState.Success(viewState.value)
    }

}