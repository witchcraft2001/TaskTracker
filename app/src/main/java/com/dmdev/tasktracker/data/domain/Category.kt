package com.dmdev.tasktracker.data.domain

import android.os.Parcelable
import com.dmdev.tasktracker.data.data.CategoryIcon
import com.dmdev.tasktracker.data.data.Colors
import kotlinx.parcelize.Parcelize

@Parcelize
data class Category(
    val id: Long,
    val name: String,
    val color: Colors,
    val icon: CategoryIcon
) : Parcelable
