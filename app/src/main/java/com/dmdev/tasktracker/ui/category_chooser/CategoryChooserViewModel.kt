package com.dmdev.tasktracker.ui.category_chooser

import androidx.lifecycle.viewModelScope
import com.dmdev.tasktracker.core.common.BaseViewModel
import com.dmdev.tasktracker.core.common.UiState
import com.dmdev.tasktracker.data.ResultWrapper
import com.dmdev.tasktracker.data.domain.Category
import com.dmdev.tasktracker.ui.task_edit.TaskEditViewState
import com.dmdev.tasktracker.usecases.GetAllCategoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryChooserViewModel @Inject constructor(
    private val allCategoriesUseCase: GetAllCategoriesUseCase,
) : BaseViewModel<UiState<List<Category>>>(UiState.Loading()) {

    init {
        loadCategories()
    }

    private fun loadCategories() {
        viewModelScope.launch {
            allCategoriesUseCase.getAllCategories().collect { result ->
                when (result) {
                    is ResultWrapper.Success -> _uiState.value =
                        UiState.Success(result.result)
                    is ResultWrapper.Loading -> _uiState.value = UiState.Loading()
                    is ResultWrapper.Error -> _uiState.value = UiState.Error(result.exception.message ?: "")
                }
            }
        }
    }

    fun reloadCategories() {
        loadCategories()
    }
}
