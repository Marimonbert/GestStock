package com.course.geststock

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.course.geststock.shared.components.CenterTopAppBarM3
import com.course.geststock.shared.event.UiEvent
import com.course.geststock.shared.navigation.NavyController
import com.course.geststock.shared.navigation.Screen
import com.course.geststock.stock.presentation.StockViewModel
import com.course.geststock.stock.presentation.components.ItemRegister
import com.course.geststock.theme.DarkBlue
import com.course.geststock.theme.Pink40

@Composable
fun MainScreen(viewModel: StockViewModel, navHostController: NavHostController) {

    var showDialog by remember { mutableStateOf(false) }
    val floatingButton = viewModel.state.value.appBarConfig.showButtonBack
    val state = viewModel.state.value

    Scaffold(
        modifier = Modifier.background(color = DarkBlue),
        topBar = {
            val appBar = viewModel.state.value.appBarConfig
            CenterTopAppBarM3(
                title = appBar.title.toString(),
                onCartClick = {
                    viewModel.onEvent(UiEvent.Navigate(Screen.Cart.route))
                },
                backClick = {
                    viewModel.onEvent(UiEvent.NavigateBack)
                },
                buttonBack = appBar.showButtonBack,
                item = state.cartItems.size
            )
        },
        floatingActionButton = {
            // Exibe o FloatingActionButton somente quando floatingButton for true
            if (!floatingButton) {
                FloatingActionButton(
                    onClick = {
                        showDialog = true // Define o estado para exibir o diálogo
                    },
                    containerColor = Pink40
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add Item",
                        tint = Color.White
                    )
                }
            }
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            NavyController(
                navController = navHostController,
                viewModel = viewModel
            )

            // Exibe o diálogo quando showDialog é verdadeiro
            if (showDialog) {
                ItemRegister(
                    item = { newItem ->
                        viewModel.onEvent(UiEvent.InsertStock(newItem)) // Adiciona o novo item no ViewModel ou na lógica correspondente
                        showDialog = false // Fecha o diálogo após adicionar o item
                    },
                    onDismiss = {
                        showDialog = false // Fecha o diálogo sem adicionar
                    }
                )
            }
        }
    }
}
