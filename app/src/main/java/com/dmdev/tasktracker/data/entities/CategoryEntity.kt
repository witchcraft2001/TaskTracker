package com.dmdev.tasktracker.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dmdev.tasktracker.data.CategoryIcon
import com.dmdev.tasktracker.data.entities.CategoryEntity.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class CategoryEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = CATEGORY_ID_FIELD)
    val id: Long,
    @ColumnInfo(name = CATEGORY_NAME_FIELD)
    val name: String,
    @ColumnInfo(name = CATEGORY_COLOR_FIELD)
    val color: Long,
    @ColumnInfo(name = CATEGORY_ICON_FIELD)
    val icon: CategoryIcon
) {
    companion object {
        const val TABLE_NAME = "categories"
        const val CATEGORY_ID_FIELD = "id"
        const val CATEGORY_NAME_FIELD = "name"
        const val CATEGORY_COLOR_FIELD = "color"
        const val CATEGORY_ICON_FIELD = "icon"
    }

    constructor() : this(0L, "", 0L, CategoryIcon.BROOM)
}

