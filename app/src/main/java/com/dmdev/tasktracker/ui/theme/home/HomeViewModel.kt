package com.dmdev.tasktracker.ui.theme.home

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class HomeViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(HomeViewState.Loading)
    val uiState: StateFlow<HomeViewState> = _uiState


}