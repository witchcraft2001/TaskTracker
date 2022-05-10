package com.dmdev.tasktracker.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController

@Composable
fun <T> NavController.GetOnceResult(keyResult: String, onResult: (T) -> Unit) {
    val valueScreenResult = currentBackStackEntry
        ?.savedStateHandle
        ?.getLiveData<T>(keyResult)?.observeAsState()

    valueScreenResult?.value?.let {
        onResult(it)

        currentBackStackEntry
            ?.savedStateHandle
            ?.remove<T>(keyResult)
    }
}

@Composable
fun <T> NavController.GetOnceResult(lifecycleOwner: LifecycleOwner, keyResult: String, onResult: (T) -> Unit) {
    currentBackStackEntry
        ?.savedStateHandle
        ?.getLiveData<T>(keyResult)?.observe(lifecycleOwner) {
            onResult(it)

            currentBackStackEntry
                ?.savedStateHandle
                ?.remove<T>(keyResult)
        }
}