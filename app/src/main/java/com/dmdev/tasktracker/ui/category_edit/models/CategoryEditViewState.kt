package com.dmdev.tasktracker.ui.category_edit.models

import com.dmdev.tasktracker.data.data.CategoryIcon
import com.dmdev.tasktracker.data.data.Colors

sealed class CategoryEditViewState {
    data class EditViewState(
        val name: String = "",
        val icon: CategoryIcon = CategoryIcon.values().first(),
        val color: Colors = Colors.values().first(),
        val isSending: Boolean = false,
        val isNameEmptyError: Boolean = false,
        val sendingError: String = ""
    ) : CategoryEditViewState()

    object SuccessViewState : CategoryEditViewState()
}