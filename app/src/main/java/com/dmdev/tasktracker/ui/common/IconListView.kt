package com.dmdev.tasktracker.ui.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.dmdev.tasktracker.data.data.CategoryIcon
import com.dmdev.tasktracker.ui.theme.BaseTheme

@Composable
fun IconListView(
    modifier: Modifier,
    selectedIcon: CategoryIcon?,
    label: String,
    onValueChange: (CategoryIcon) -> Unit
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
                items(CategoryIcon.values().size) { index ->
                    val icon = CategoryIcon.values()[index]
                    Card(
                        modifier = Modifier
                            .size(48.dp)
                            .clickable { onValueChange(icon) },
                        shape = RoundedCornerShape(6.dp),
                        border = BorderStroke(
                            2.dp,
                            if (icon == selectedIcon) BaseTheme.colors.primaryBlue else BaseTheme.colors.bgGray
                        ),
                        backgroundColor = Color.Transparent,
                        elevation = 0.dp
                    ) {
                        Box(
                            modifier = Modifier
                                .padding(8.dp)
                                .size(32.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = icon.resourceId),
                                contentDescription = "icon",
                                tint = BaseTheme.colors.textBlack,
                                modifier = Modifier
                                    .size(24.dp)
                                    .align(Alignment.Center)
                            )
                        }
                    }
                }
            }
        }
    }
}
