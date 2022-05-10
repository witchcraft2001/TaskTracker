package com.dmdev.tasktracker.ui.task_edit

import com.dmdev.tasktracker.core.common.BaseViewModel
import com.dmdev.tasktracker.core.common.SimpleUiState
import com.dmdev.tasktracker.core.common.UiState
import com.dmdev.tasktracker.data.domain.Category
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class TaskEditViewModel @Inject constructor() : BaseViewModel<UiState<TaskEditViewState>>(UiState.Loading()) {
    private val _state = MutableStateFlow(TaskEditViewState())
    val state: StateFlow<TaskEditViewState> = _state

    val id: Long? = null

    init {
        _uiState.value = UiState.Success(state.value)
    }

    fun save() {

    }

    fun onNameUpdated(value: String) {
        if (value == state.value.name) {
            return
        }
        _state.value = state.value.copy(name = value, nameError = value.isEmpty())
        updateUiState()
    }

    fun onCategorySelected(item: Category?) {
        if (item != null && item != state.value.category) {
            _state.value = state.value.copy(category = item, categoryError = false)
        }

        updateUiState()
    }

    private fun updateUiState() {
        _uiState.value = UiState.Success(state.value)
    }
}