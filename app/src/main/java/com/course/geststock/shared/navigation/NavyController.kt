package com.course.geststock.shared.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.course.geststock.shared.components.UiEventFlow
import com.course.geststock.shared.event.UiEvent
import com.course.geststock.shared.state.AppBarState
import com.course.geststock.stock.presentation.ShopCartScreen
import com.course.geststock.stock.presentation.StockScreen
import com.course.geststock.stock.presentation.StockViewModel


@Composable
fun NavyController(
    navController: NavHostController,
    viewModel: StockViewModel,
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Stock.route
    ) {
        composable(Screen.Stock.route) {

            UiEventFlow(
                eventFlow = viewModel.eventFlow,
                navController = navController
            )

            val appBarState = AppBarState(
                title = "Artigos ",
                showButtonBack = false,
            )
            viewModel.onEvent(UiEvent.UpdateAppBarState(appBarState))

            StockScreen(
                viewModel = viewModel
            )
        }

        composable(Screen.Cart.route) {

            UiEventFlow(
                eventFlow = viewModel.eventFlow,
                navController = navController
            )

            val appBarState = AppBarState(
                title = "Itens Adicionados ",
                showButtonBack = true,
            )
            viewModel.onEvent(UiEvent.UpdateAppBarState(appBarState))

            ShopCartScreen(
                viewModel = viewModel
            )
        }
    }
}
