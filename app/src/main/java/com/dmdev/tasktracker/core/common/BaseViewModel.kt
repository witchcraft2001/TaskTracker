package com.dmdev.tasktracker.core.common

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class BaseViewModel<State>(
    initialViewState: State
) : ViewModel() {
    protected val _uiState = MutableStateFlow(initialViewState)
    val uiState: StateFlow<State> = _uiState
}