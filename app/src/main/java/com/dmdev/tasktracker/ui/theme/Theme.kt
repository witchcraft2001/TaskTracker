package com.dmdev.tasktracker.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TaskTrackerTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = baseLightPalette

    val typography = BaseTypography(
        title1 = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp
        ),
        title2 = TextStyle(
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp
        ),
        title3 = TextStyle(
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp
        ),
        text15 = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 15.sp
        ),
        text15M = TextStyle(
            fontWeight = FontWeight.Medium,
            fontSize = 15.sp
        ),
        text15B = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 15.sp
        ),
        text12R = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp
        ),
        text16 = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp
        ),
        text16B = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        ),
        text14M = TextStyle(
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp
        ),
        text14R = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp
        )
    )

    val shape = BaseShape(
        cornersStyle = RoundedCornerShape(4.dp)
    )

    CompositionLocalProvider(
        LocalColors provides colors,
        LocalTypography provides typography,
        LocalShape provides shape,
        content = content
    )
}