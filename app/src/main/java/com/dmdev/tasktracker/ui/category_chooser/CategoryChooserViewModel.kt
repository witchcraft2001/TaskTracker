package com.dmdev.tasktracker.ui.category_chooser

import androidx.lifecycle.viewModelScope
import com.dmdev.tasktracker.core.common.BaseViewModel
import com.dmdev.tasktracker.core.common.BaseViewModelEventHandler
import com.dmdev.tasktracker.core.common.UiState
import com.dmdev.tasktracker.data.ResultWrapper
import com.dmdev.tasktracker.data.domain.Category
import com.dmdev.tasktracker.ui.category_chooser.models.CategoryChooserEvent
import com.dmdev.tasktracker.ui.category_edit.models.CategoryEditEvent
import com.dmdev.tasktracker.ui.category_edit.models.CategoryEditViewState
import com.dmdev.tasktracker.usecases.GetAllCategoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryChooserViewModel @Inject constructor(
    private val allCategoriesUseCase: GetAllCategoriesUseCase,
) : BaseViewModelEventHandler<UiState<List<Category>>, CategoryChooserEvent>(UiState.Loading()) {

    private fun loadCategories() {
        viewModelScope.launch {
            allCategoriesUseCase.execute().collect { result ->
                when (result) {
                    is ResultWrapper.Success -> _uiState.value =
                        UiState.Success(result.result)
                    is ResultWrapper.Loading -> _uiState.value = UiState.Loading()
                    is ResultWrapper.Error -> _uiState.value = UiState.Error(result.exception.message ?: "")
                }
            }
        }
    }

    override fun obtainEvent(event: CategoryChooserEvent) {
        val currentViewState = _uiState.value
        reduce(event, currentViewState)
    }

    private fun reduce(event: CategoryChooserEvent, currentViewState: UiState<*>) {
        when (event) {
            is CategoryChooserEvent.ReloadEvent -> {
                _uiState.value = UiState.Loading()
                loadCategories()
            }
        }
    }
}

