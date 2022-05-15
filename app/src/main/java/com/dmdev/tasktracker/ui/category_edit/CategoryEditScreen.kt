package com.dmdev.tasktracker.ui.category_edit

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dmdev.tasktracker.R
import com.dmdev.tasktracker.data.data.CategoryIcon
import com.dmdev.tasktracker.data.data.Colors
import com.dmdev.tasktracker.data.domain.Category
import com.dmdev.tasktracker.navigation.NavState
import com.dmdev.tasktracker.ui.ButtonTextCenter
import com.dmdev.tasktracker.ui.InputField
import com.dmdev.tasktracker.ui.ToolbarTextWithBack
import com.dmdev.tasktracker.ui.category_edit.models.CategoryEditEvent
import com.dmdev.tasktracker.ui.category_edit.models.CategoryEditViewState
import com.dmdev.tasktracker.ui.common.ColorListView
import com.dmdev.tasktracker.ui.common.IconListView
import com.dmdev.tasktracker.ui.theme.TaskTrackerTheme

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CategoryEditScreen(
    navState: NavState,
    category: Category?,
    vm: CategoryEditViewModel
) {
    val viewState = vm.uiState.collectAsState()
    val keyboardController = LocalSoftwareKeyboardController.current

    Surface {
        Column {
            ToolbarTextWithBack(title = stringResource(id = R.string.new_task)) {
                navState.navigateBack()
            }
            when (val state = viewState.value) {
                is CategoryEditViewState.SuccessViewState -> {
                    vm.obtainEvent(CategoryEditEvent.DropState)
                    navState.navigateBack()
                }
                is CategoryEditViewState.EditViewState -> {
                    CategoryEditView(
                        state = state,
                        onNameChanged = { vm.obtainEvent(CategoryEditEvent.NameChanged(it)) },
                        onIconChanged = { vm.obtainEvent(CategoryEditEvent.IconChanged(it)) },
                        onColorChanged = { vm.obtainEvent(CategoryEditEvent.ColorChanged(it)) },
                        onSaveClicked = {
                            keyboardController?.hide()
                            vm.obtainEvent(CategoryEditEvent.SaveClicked)
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun CategoryEditView(
    state: CategoryEditViewState.EditViewState,
    onNameChanged: (name: String) -> Unit,
    onIconChanged: (icon: CategoryIcon) -> Unit,
    onColorChanged: (color: Colors) -> Unit,
    onSaveClicked: () -> Unit
) {
    Column(modifier = Modifier.padding(16.dp)) {

        InputField(
            modifier = Modifier.padding(top = 16.dp),
            value = state.name,
            label = stringResource(R.string.text_category),
            hint = stringResource(R.string.text_not_specified),
            isError = state.isNameEmptyError,
            errorText = if (state.isNameEmptyError) stringResource(R.string.text_not_specified) else "",
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                capitalization = KeyboardCapitalization.Sentences
            ),
            onValueChange = onNameChanged
        )

        IconListView(
            modifier = Modifier.padding(top = 16.dp),
            label = stringResource(R.string.text_icon),
            selectedIcon = state.icon,
            onValueChange = onIconChanged
        )

        ColorListView(
            modifier = Modifier.padding(top = 16.dp),
            label = stringResource(R.string.text_color),
            selectedColor = state.color,
            onValueChange = onColorChanged
        )

        Spacer(modifier = Modifier.weight(1f))

        ButtonTextCenter(
            text = stringResource(R.string.button_save),
            onClick = onSaveClicked
        )
    }
}

@ExperimentalFoundationApi
@Preview
@Composable
fun CategoryEditView_Preview() {
    TaskTrackerTheme {
        CategoryEditView(
            state = CategoryEditViewState.EditViewState(),
            onNameChanged = {},
            onIconChanged = {},
            onColorChanged = {},
            onSaveClicked = {}
        )
    }
}