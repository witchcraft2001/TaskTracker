package com.dmdev.tasktracker.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle

data class BaseColors(
    val primaryDarkBlue: Color,
    val primaryLightBlue: Color,
    val primaryBrandColor: Color,
    val primaryYellow: Color,
    val primaryBlue: Color,
    val textBlack: Color,
    val textDarkGray: Color,
    val textGray: Color,
    val textWhite: Color,
    val textRed: Color,
    val textGreen: Color,
    val bgGray: Color,
    val bgRed: Color,
    val bgBlue: Color,
    val bgLightBlue: Color,
    val background: Color,
)

data class BaseTypography(
    val title1: TextStyle,
    val title2: TextStyle,
    val title3: TextStyle,
    val text12R: TextStyle,
    val text14M: TextStyle,
    val text14R: TextStyle,
    val text15: TextStyle,
    val text15M: TextStyle,
    val text15B: TextStyle,
    val text16: TextStyle,
    val text16B: TextStyle
)

data class BaseShape(
    val cornersStyle: Shape
)

object BaseTheme{
    val colors: BaseColors
    @Composable
    get() = LocalColors.current

    val typography: BaseTypography
    @Composable
    get() = LocalTypography.current

    val shape: BaseShape
    @Composable
    get() = LocalShape.current
}

val LocalColors = staticCompositionLocalOf<BaseColors> {
    error("No colors provided")
}

val LocalTypography = staticCompositionLocalOf<BaseTypography> {
    error("No font provided")
}

val LocalShape = staticCompositionLocalOf<BaseShape> {
    error("No shapes provided")
}

