package com.course.geststock.stock.presentation

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.course.geststock.shared.event.UiEvent
import com.course.geststock.stock.data.DataStock
import com.course.geststock.stock.presentation.components.EmptyListItem
import com.course.geststock.stock.presentation.components.StockItem
import com.course.geststock.theme.LightGreen
import com.course.geststock.theme.LightGreen1

@Composable
fun StockScreen(viewModel: StockViewModel) {

    val stockList = viewModel.state.value.stocks

    val context = LocalContext.current

    // Estado para controlar o diálogo de confirmação
    var showConfirmationDialog by remember { mutableStateOf(false) }
    var selectedItemToRemove by remember { mutableStateOf<DataStock?>(null) }

    Column(modifier = Modifier.fillMaxSize()) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.CenterHorizontally)
                .padding(top = 8.dp, end = 8.dp, start = 8.dp),
            shape = RoundedCornerShape(
                topStart = 30.dp,
                topEnd = 30.dp
            )
        ) {
            if (stockList.isEmpty()) {
                EmptyListItem()
            } else {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(stockList.size) { index ->
                        val item = stockList[index]

                        val selectedQuantity = viewModel.state.value.cartItems[item] ?: 0.0

                        StockItem(
                            item = item,
                            backgroundColor = if (index % 2 == 0) LightGreen else LightGreen1,
                            selectedQuantity = selectedQuantity,
                            onAddClick = {
                                if (selectedQuantity < item.quantity) {
                                    viewModel.onEvent(UiEvent.AddToCart(item))
                                } else {
                                    Toast.makeText(
                                        context,
                                        "Quantidade máxima atingida para este item",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            },
                            onRemoveClick = {

                                selectedItemToRemove = item // Define o item a ser removido
                                showConfirmationDialog = true // Exibe o diálogo
                            }
                        )
                    }
                }
            }
        }
    }

    // Diálogo de confirmação para remover o item
    if (showConfirmationDialog && selectedItemToRemove != null) {
        AlertDialog(
            onDismissRequest = { showConfirmationDialog = false },
            title = { Text("Remover Item") },
            text = { Text("Tem certeza de que deseja remover este item do estoque?") },
            confirmButton = {
                TextButton(onClick = {
                    selectedItemToRemove?.let { item ->
                        viewModel.onEvent(UiEvent.DeleteStock(item)) // Dispara o evento de remoção
                    }
                    selectedItemToRemove = null // Reseta o item selecionado
                    showConfirmationDialog = false // Fecha o diálogo
                }) {
                    Text("Sim")
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    selectedItemToRemove = null // Reseta o item selecionado
                    showConfirmationDialog = false // Fecha o diálogo
                }) {
                    Text("Não")
                }
            }
        )
    }
}

@Preview
@Composable
fun StockScreenPreview() {
    // StockScreen(stockList = stockItemsMock)
}

