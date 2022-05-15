package com.dmdev.tasktracker.data.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CategoryData(
    @PrimaryKey val id: Long,
    @ColumnInfo(name = CATEGORY_NAME_FIELD) val name: String,
    @ColumnInfo(name = CATEGORY_COLOR_FIELD) val color: ULong,
    @ColumnInfo(name = CATEGORY_ICON_FIELD) val icon: CategoryIcon
) {
    companion object {
        const val TABLE_NAME = "categories"
        const val CATEGORY_NAME_FIELD = "name"
        const val CATEGORY_COLOR_FIELD = "color"
        const val CATEGORY_ICON_FIELD = "icon"
    }
}

