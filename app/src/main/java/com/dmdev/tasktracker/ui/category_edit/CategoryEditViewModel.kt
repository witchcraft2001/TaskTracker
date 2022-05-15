package com.dmdev.tasktracker.ui.category_edit

import androidx.lifecycle.viewModelScope
import com.dmdev.tasktracker.core.common.BaseViewModelEventHandler
import com.dmdev.tasktracker.ui.category_edit.models.CategoryEditEvent
import com.dmdev.tasktracker.ui.category_edit.models.CategoryEditViewState
import com.dmdev.tasktracker.usecases.AddCategoryUseCase
import com.dmdev.tasktracker.usecases.UpdateCategoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryEditViewModel @Inject constructor(
    private val addCategoryUseCase: AddCategoryUseCase,
    private val updateCategoryUseCase: UpdateCategoryUseCase,
) : BaseViewModelEventHandler<CategoryEditViewState, CategoryEditEvent>(CategoryEditViewState.EditViewState()) {

    private var id: Long? = null

    override fun obtainEvent(event: CategoryEditEvent) {
        when (val currentViewState = _uiState.value) {
            is CategoryEditViewState.EditViewState -> reduce(event, currentViewState)
            is CategoryEditViewState.SuccessViewState -> reduce(event, currentViewState)
        }
    }

    private fun reduce(event: CategoryEditEvent, currentState: CategoryEditViewState.EditViewState) {
        when (event) {
            is CategoryEditEvent.NameChanged -> _uiState.value =
                currentState.copy(name = event.name, isNameEmptyError = event.name.isEmpty())
            is CategoryEditEvent.IconChanged -> _uiState.value = currentState.copy(icon = event.icon)
            is CategoryEditEvent.ColorChanged -> _uiState.value = currentState.copy(color = event.color)
            is CategoryEditEvent.SaveClicked -> save(currentState)
        }
    }

    private fun reduce(event: CategoryEditEvent, currentState: CategoryEditViewState.SuccessViewState) {
        when (event) {
            is CategoryEditEvent.DropState -> _uiState.value = CategoryEditViewState.EditViewState()
        }
    }

    private fun save(currentState: CategoryEditViewState.EditViewState) {
        if (currentState.name.isEmpty()) {
            _uiState.value = currentState.copy(isNameEmptyError = true)
            return
        }
        viewModelScope.launch {
            _uiState.value = currentState.copy(isSending = true)
            try {
                with(currentState) {
                    if (id != null) {
                        updateCategoryUseCase.execute(id ?: 0, name, icon, color)
                    } else {
                        addCategoryUseCase.execute(name, icon, color)
                    }
                    _uiState.value = CategoryEditViewState.SuccessViewState
                }
            } catch (e: Throwable) {
                _uiState.value = currentState.copy(sendingError = e.message ?: "Something was wrong", isSending = false)
            }
        }
    }
}