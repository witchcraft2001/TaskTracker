package com.dmdev.tasktracker.ui.task_edit

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.dmdev.tasktracker.R
import com.dmdev.tasktracker.core.common.UiState
import com.dmdev.tasktracker.data.Constants.CATEGORY_CHOOSER_RESULT_KEY
import com.dmdev.tasktracker.data.domain.Category
import com.dmdev.tasktracker.navigation.GetOnceResult
import com.dmdev.tasktracker.navigation.NavState
import com.dmdev.tasktracker.ui.ButtonTextCenter
import com.dmdev.tasktracker.ui.InputField
import com.dmdev.tasktracker.ui.LoadingBox
import com.dmdev.tasktracker.ui.ToolbarTextWithBack
import com.dmdev.tasktracker.ui.theme.BaseTheme

@Composable
fun TaskEdit(navState: NavState, vm: TaskEditViewModel) {
    Surface(modifier = Modifier.fillMaxSize(), color = BaseTheme.colors.background) {
        Column {
            ToolbarTextWithBack(title = stringResource(id = R.string.new_task)) {
                navState.navigateBack()
            }

            when (val state = vm.uiState.collectAsState().value) {
                is UiState.Loading -> {
                    LoadingBox()
                }
                is UiState.Success -> TaskEditForm(navState, state.value, vm)
                is UiState.Finished -> {
                    vm.dropToLoadState()
                    navState.navigateBack()
                }
                else -> throw IllegalStateException("Unknown state $state")
            }

        }
    }
}

@Composable
fun TaskEditForm(
    navState: NavState,
    state: TaskEditViewState,
    vm: TaskEditViewModel
) {
    Column(modifier = Modifier.padding(16.dp)) {

        navState.navController.currentBackStackEntry?.also { entry ->
            navState.navController.GetOnceResult<Category>(entry, keyResult = CATEGORY_CHOOSER_RESULT_KEY) {
                vm.onCategorySelected(it)
            }
        }

        var inputName by remember {
            mutableStateOf(state.name)
        }

        InputField(
            modifier = Modifier.padding(top = 16.dp),
            value = state.category?.name ?: "",
            label = stringResource(R.string.text_category),
            hint = stringResource(R.string.text_not_specified),
            isError = state.categoryError,
            errorText = if (state.categoryError) stringResource(R.string.text_not_specified) else "",
            click = {
                navState.navigateToCategoryChooser(state.category?.id)
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                capitalization = KeyboardCapitalization.Sentences
            ),
            onValueChange = {}
        )

        InputField(
            modifier = Modifier.padding(top = 16.dp),
            value = inputName,
            label = stringResource(id = R.string.text_name),
            hint = stringResource(R.string.text_not_specified),
            isError = state.nameError,
            errorText = if (state.nameError) stringResource(R.string.text_not_specified) else "",
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text
            ),
            onValueChange = {
                inputName = it
                vm.onNameUpdated(it)
            }
        )

        Spacer(modifier = Modifier.weight(1f))

        ButtonTextCenter(
            text = stringResource(R.string.button_save),
        ) {
            vm.save()
        }
    }
}
