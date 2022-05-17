package com.dmdev.tasktracker.ui.task_edit

import androidx.lifecycle.viewModelScope
import com.dmdev.tasktracker.core.common.BaseViewModel
import com.dmdev.tasktracker.core.common.UiState
import com.dmdev.tasktracker.core.extensions.getOrElse
import com.dmdev.tasktracker.data.ResultWrapper
import com.dmdev.tasktracker.data.domain.Category
import com.dmdev.tasktracker.usecases.AddTaskUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskEditViewModel @Inject constructor(
    val addTaskUseCase: AddTaskUseCase
) : BaseViewModel<UiState<TaskEditViewState>>(UiState.Loading()) {
    private val _state = MutableStateFlow(TaskEditViewState())
    val state: StateFlow<TaskEditViewState> = _state

    val id: Long? = null

    init {
        _uiState.value = UiState.Success(state.value)
    }

    fun save() {
        if (state.value.category == null) {
            _state.value = state.value.copy(categoryError = true)
            updateUiState()
        } else {
            viewModelScope.launch(Dispatchers.IO) {
                state.value.category?.let { category ->
                    val name = state.value.name.getOrElse("unnamed")
                    addTaskUseCase.execute(name, category).collect { result ->
                        when(result) {
                            is ResultWrapper.Success -> _uiState.value = UiState.Finished()
                            is ResultWrapper.Error -> _uiState.value = UiState.Error(result.exception.message.getOrElse(""))
                            is ResultWrapper.Loading -> _uiState.value = UiState.Loading()
                            else -> throw IllegalStateException("Unknown state $result")
                        }
                    }
                }
            }
        }
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

    fun dropToLoadState() {
        _uiState.value = UiState.Loading()
    }
}