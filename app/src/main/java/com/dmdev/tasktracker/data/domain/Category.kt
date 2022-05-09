package com.dmdev.tasktracker.data.domain

import android.os.Parcelable
import com.dmdev.tasktracker.data.data.CategoryIcon
import kotlinx.parcelize.Parcelize

@Parcelize
data class Category(
    val id: Long,
    val name: String,
    val color: Long,
    val icon: CategoryIcon
) : Parcelable
