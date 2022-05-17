package com.dmdev.tasktracker.data

import androidx.compose.ui.graphics.Color

enum class Colors(val value: Color) {
    RED(Color(0xFFEF9A9A)),
    PINK(Color(0xFFF48FB1)),
    PURPLE(Color(0xFFCE93D8)),
    DEEP_PURPLE(Color(0xFFB39DDB)),
    INDIGO(Color(0xFF9FA8DA)),
    BLUE(Color(0xFF90CAF9)),
    LIGHT_BLUE(Color(0xFF81D4FA)),
    CIAN(Color(0xFF80DEEA)),
    GREEN(Color(0xFFA5D6A7)),
    LIGHT_GREEN(Color(0xFFC5E1A5)),
    LIME(Color(0xFFE6EE9C)),
    YELLOW(Color(0xFFFFF59D)),
    AMBER(Color(0xFFFFE082)),
    ORANGE(Color(0xFFFFCC80)),
    DEEP_ORANGE(Color(0xFFFFAB91)),
    BROWN(Color(0xFFBCAAA4)),
    GRAY(Color(0xFFEEEEEE)),
    BLUE_GRAY(Color(0xFFB0BEC5)),
}

fun Long.parseColor() : Colors {
    return Colors.values().firstOrNull { color -> color.value.value.toLong() == this } ?: Colors.values().first()
}