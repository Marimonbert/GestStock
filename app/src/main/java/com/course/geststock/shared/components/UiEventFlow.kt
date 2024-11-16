package com.course.geststock.shared.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavHostController
import com.course.geststock.shared.event.UiEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

@Composable
fun <T> ObserveAsEvents(flow: Flow<T>, onEvent: (T) -> Unit) {
    val lifecycleOwner = LocalLifecycleOwner.current
    LaunchedEffect(flow, lifecycleOwner.lifecycle) {
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            withContext(Dispatchers.Main.immediate) {
                flow.collect { onEvent(it) }
            }
        }
    }
}

@Composable
fun UiEventFlow(
    eventFlow: Flow<UiEvent>,
    navController: NavHostController,
) {

    ObserveAsEvents(eventFlow) { event ->
        when (event) {

            is UiEvent.Navigate -> {

                val currentRoute = navController.currentBackStackEntry?.destination?.route
                if (currentRoute != event.route) {

                    navController.navigate(event.route)
                }
            }

            UiEvent.NavigateBack -> {
                navController.popBackStack()
            }

            else -> {}
        }
    }

}





