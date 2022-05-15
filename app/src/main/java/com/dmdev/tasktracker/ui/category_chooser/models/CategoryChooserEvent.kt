package com.dmdev.tasktracker.ui.category_chooser.models

sealed class CategoryChooserEvent {
    object ReloadEvent: CategoryChooserEvent()
}