package com.dmdev.tasktracker.ui.category_chooser

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.dmdev.tasktracker.R
import com.dmdev.tasktracker.core.common.UiState
import com.dmdev.tasktracker.core.extensions.getOrElse
import com.dmdev.tasktracker.data.Constants.CATEGORY_CHOOSER_RESULT_KEY
import com.dmdev.tasktracker.data.domain.Category
import com.dmdev.tasktracker.navigation.NavState
import com.dmdev.tasktracker.ui.*
import com.dmdev.tasktracker.ui.theme.BaseTheme

@Composable
fun CategoryChooser(navState: NavState, categoryId: Long?, vm: CategoryChooserViewModel) {
    LaunchedEffect(Unit) {
        vm.reloadCategories()
    }
    Surface(modifier = Modifier.fillMaxSize(), color = BaseTheme.colors.background) {
        Column {
            ToolbarTextWithBackAndActionButton(
                title = stringResource(R.string.text_category),
                actionIcon = {
                    IconButton(
                        onClick = {
                                  navState.navigateToCategoryEdit(null)
                                  },
                        modifier = Modifier
                            .size(24.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_add),
                            contentDescription = stringResource(R.string.hint_add_category),
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            ) {
                navState.navigateBack()
            }

            when (val state = vm.uiState.collectAsState().value) {
                is UiState.Loading -> {
                    LoadingBox()
                }
                is UiState.Success -> {
                    CategoryList(
                        state.value,
                        categoryId,
                        onItemClicked = {
                            navState.navController.previousBackStackEntry
                                ?.savedStateHandle
                                ?.set(CATEGORY_CHOOSER_RESULT_KEY, it)
                            navState.navigateBack()
                        })
                }
                is UiState.Error -> {
                    ErrorBox(
                        message = state.message.getOrElse(stringResource(R.string.text_something_was_wrong)),
                        buttonText = stringResource(R.string.button_reload)
                    ) {
                        vm.reloadCategories()
                    }
                }
            }
        }
    }
}

@Composable
fun CategoryList(
    items: List<Category>,
    selectedItemId: Long?,
    onItemClicked: (Category?) -> Unit
) {
    Column {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(0.dp),
            modifier = Modifier
                .weight(1f)
        ) {
            items(items.count()) { index ->
                SelectedItemWithIcon(
                    items[index].name,
                    leadingIcon= {
                        Box(
                            modifier = Modifier
                                .size(48.dp)
                                .clip(CircleShape)
                                .background(items[index].color.value)
                        ) {
                            Icon(
                                painter = painterResource(id = items[index].icon.resourceId),
                                contentDescription = items[index].name,
                                tint = BaseTheme.colors.textWhite,
                                modifier = Modifier
                                    .size(24.dp)
                                    .align(Alignment.Center)
                            )
                        }
                },
                    isSelected = items[index].id == selectedItemId
                ) {
                    onItemClicked(items[index])
                }
                if (index < items.count() - 1) {
                    Divider(
                        color = BaseTheme.colors.bgGray,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                }
            }
        }
        ButtonTextCenter(
            text = stringResource(R.string.button_add_category),
            modifier = Modifier.padding(16.dp)
        ) { onItemClicked(null) }
    }
}
