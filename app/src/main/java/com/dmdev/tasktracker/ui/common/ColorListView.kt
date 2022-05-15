package com.dmdev.tasktracker.ui.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.dmdev.tasktracker.data.data.Colors
import com.dmdev.tasktracker.ui.theme.BaseTheme

@Composable
fun ColorListView(
    modifier: Modifier,
    label: String,
    selectedColor: Colors,
    onValueChange: (color: Colors) -> Unit
) {
    Column(modifier = modifier) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = label,
                color = BaseTheme.colors.textDarkGray,
                style = BaseTheme.typography.text15,
                modifier = Modifier.align(Alignment.CenterStart)
            )
        }
        Box(
            modifier = Modifier
                .padding(top = 8.dp)
                .fillMaxWidth()
                .wrapContentSize(Alignment.TopStart)
                .clip(RoundedCornerShape(6.dp))
                .background(BaseTheme.colors.bgGray),
            contentAlignment = Alignment.CenterStart
        ) {
            LazyRow {
                items(Colors.values().size) { index ->
                    val color = Colors.values()[index] as Colors
                    Card(
                        modifier = Modifier
                            .size(48.dp)
                            .clickable { onValueChange(color) },
                        shape = RoundedCornerShape(6.dp),
                        border = BorderStroke(
                            2.dp,
                            if (color == selectedColor) BaseTheme.colors.primaryBlue else BaseTheme.colors.bgGray
                        ),
                        backgroundColor = Color.Transparent,
                        elevation = 0.dp
                    ) {
                        Box(
                            modifier = Modifier
                                .padding(8.dp)
                                .size(32.dp)
                        ) {
                            Card(
                                modifier = Modifier
                                    .size(24.dp)
                                    .align(Alignment.Center),
                                shape = RoundedCornerShape(6.dp),
                                border = BorderStroke(0.5.dp, BaseTheme.colors.textBlack),
                                backgroundColor = color.value,
                                elevation = 0.dp
                            ) { }
                        }
                    }
                }
            }
        }
    }
}