package com.dmdev.tasktracker.data.converters

import androidx.room.TypeConverter
import com.dmdev.tasktracker.data.CategoryIcon

class CategoryIconTypeConverter {
    @TypeConverter
    fun fromCategoryIcon(icon: CategoryIcon) : String = icon.name

    @TypeConverter
    fun toCategoryIcon(iconString: String) : CategoryIcon = CategoryIcon.valueOf(iconString)
}