package com.dmdev.tasktracker.ui.category_edit.models

import com.dmdev.tasktracker.data.CategoryIcon
import com.dmdev.tasktracker.data.Colors

sealed class CategoryEditEvent {
    data class NameChanged(val name: String) : CategoryEditEvent()
    data class IconChanged(val icon: CategoryIcon) : CategoryEditEvent()
    data class ColorChanged(val color: Colors) : CategoryEditEvent()
    object DropState: CategoryEditEvent()
    object SaveClicked: CategoryEditEvent()
}