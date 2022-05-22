package com.dmdev.tasktracker.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.dmdev.tasktracker.R
import com.dmdev.tasktracker.ui.theme.BaseTheme

@Composable
fun DropdownList(
    modifier: Modifier = Modifier,
    items: List<Any>,
    selectedItem: Any?,
    label: String,
    hint: String,
    isError: Boolean = false,
    errorText: String = "",
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    trailingText: String = "",
    trailingTint: Color = BaseTheme.colors.textRed,
    onItemSelected: ((Any) -> Unit)? = null,
    onSelectedIndexChanged: ((Int) -> Unit)? = null
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedIndex by remember { mutableStateOf(items.indexOf(selectedItem)) }

    Column(modifier = modifier.clickable { expanded = true }) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = label,
                color = BaseTheme.colors.textDarkGray,
                style = BaseTheme.typography.text15,
                modifier = Modifier.align(Alignment.CenterStart)
            )
            if (trailingText.isNotEmpty()) {
                Text(
                    text = trailingText,
                    color = trailingTint,
                    style = BaseTheme.typography.text15,
                    modifier = Modifier.align(Alignment.CenterEnd)
                )
            }
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
            Row(modifier = Modifier
                .padding(all = 16.dp)
                .fillMaxWidth()) {
                if (leadingIcon != null) {
                    leadingIcon()
                }
                if (selectedIndex == -1) {
                    Text(
                        hint,
                        color = BaseTheme.colors.textGray,
                        style = BaseTheme.typography.text15
                    )
                } else {
                    Text(
                        items[selectedIndex].toString(),
                        color = BaseTheme.colors.textBlack,
                        style = BaseTheme.typography.text15
                    )
                }
                if (trailingIcon != null) {
                    trailingIcon()
                }
            }

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.fillMaxWidth()
            ) {
                items.forEachIndexed { index, s ->
                    DropdownMenuItem(onClick = {
                        selectedIndex = index
                        expanded = false
                        onItemSelected?.invoke(items[index])
                        onSelectedIndexChanged?.invoke(selectedIndex)
                    }) {
                        Text(text = s.toString())
                    }
                }
            }
        }
        if (isError) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(top = 8.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_info_circle),
                    contentDescription = null,
                    tint = BaseTheme.colors.textRed,
                    modifier = Modifier.size(16.dp)
                )
                Text(
                    text = errorText,
                    color = BaseTheme.colors.textRed,
                    style = BaseTheme.typography.text12R,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
    }
}