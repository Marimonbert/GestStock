package com.course.geststock.stock.presentation

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.course.geststock.shared.event.UiEvent
import com.course.geststock.stock.data.DataStock
import com.course.geststock.stock.presentation.components.RoundedTextLabel
import com.course.geststock.stock.presentation.components.ShopItem
import com.course.geststock.theme.DarkBlue
import com.course.geststock.theme.Green
import com.course.geststock.theme.LightGreen
import com.course.geststock.theme.LightGreen1

@Composable
fun ShopCartScreen(viewModel: StockViewModel) {
    val state = viewModel.state.value
    val cartItems = state.cartItems

    var showConfirmationDialog by remember { mutableStateOf(false) }
    var showEditDialog by remember { mutableStateOf(false) }
    var newQuantity by remember { mutableStateOf("") }
    var selectedItem by remember { mutableStateOf<DataStock?>(null) }
    val context = LocalContext.current

    // Calcula o total
    val total = cartItems.entries.sumOf { (item, quantity) -> item.price * quantity }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Surface(
            modifier = Modifier
                .padding(top = 20.dp)
                .align(Alignment.CenterHorizontally)
                .fillMaxSize(),
            shape = RoundedCornerShape(
                topStart = 30.dp,
                topEnd = 30.dp,
            )
        ) {
            Column(
                modifier = Modifier
                    .background(DarkBlue)
                    .fillMaxSize()
            ) {
                Surface(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(
                        topStart = 30.dp,
                        topEnd = 30.dp,
                        bottomStart = 60.dp
                    )
                ) {
                    if (cartItems.isEmpty()) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "O carrinho está vazio",
                                style = MaterialTheme.typography.bodyMedium,
                                color = DarkBlue
                            )
                        }
                    } else {
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(horizontal = 20.dp)
                                .padding(top = 20.dp)
                        ) {
                            items(cartItems.keys.toList().size) { index ->
                                val item = cartItems.keys.toList()[index]
                                val quantity = cartItems[item] ?: 0.0

                                ShopItem(
                                    index = index,
                                    item = item,
                                    quantities = quantity,
                                    backgroundColor = if (index % 2 == 0) LightGreen1 else LightGreen,
                                    onRemove = {
                                        viewModel.onEvent(UiEvent.RemoveFromCart(item))
                                    },
                                    onEdit = {
                                        selectedItem = item
                                        newQuantity = quantity.toString()
                                        showEditDialog = true
                                    }
                                )

                                Spacer(modifier = Modifier.height(2.dp))
                            }
                        }
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Total: R$ ${"%.2f".format(total)}",
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.White,
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )

                    RoundedTextLabel(
                        modifier = Modifier
                            .height(45.dp)
                            .wrapContentWidth(),
                        text = "Fechar Venda",
                        colorText = Color.White,
                        colorBackground = Green,
                        click = {
                            showConfirmationDialog = true
                        }
                    )
                }
            }
        }
    }

    // Diálogo de confirmação para fechar a venda
    if (showConfirmationDialog) {
        AlertDialog(
            onDismissRequest = { showConfirmationDialog = false },
            title = { Text("Fechar Venda") },
            text = { Text("Tem certeza de que deseja fechar a venda?") },
            confirmButton = {
                TextButton(onClick = {
                    viewModel.onEvent(UiEvent.CloseSale)
                    showConfirmationDialog = false
                }) {
                    Text("Sim")
                }
            },
            dismissButton = {
                TextButton(onClick = { showConfirmationDialog = false }) {
                    Text("Cancelar")
                }
            }
        )
    }

    // Diálogo de edição de quantidade
    if (showEditDialog && selectedItem != null) {
        AlertDialog(
            onDismissRequest = { showEditDialog = false },
            title = { Text("Editar Quantidade") },
            text = {
                Column {
                    Text("Insira uma nova quantidade (máximo: ${selectedItem?.quantity})")
                    OutlinedTextField(
                        value = newQuantity,
                        onValueChange = { newQuantity = it },
                        label = { Text("Quantidade") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
                }
            },
            confirmButton = {
                TextButton(onClick = {
                    val quantity = newQuantity.toDoubleOrNull()
                    if (quantity != null && quantity in 0.0..(selectedItem?.quantity ?: 0.0)) {
                        viewModel.onEvent(UiEvent.UpdateItem(selectedItem!!, quantity))
                        showEditDialog = false
                    } else {
                        Toast.makeText(
                            context,
                            "Quantidade inválida!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }) {
                    Text("Salvar")
                }
            },
            dismissButton = {
                TextButton(onClick = { showEditDialog = false }) {
                    Text("Cancelar")
                }
            }
        )
    }
}
