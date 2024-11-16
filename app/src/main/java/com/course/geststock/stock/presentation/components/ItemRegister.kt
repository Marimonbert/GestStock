package com.course.geststock.stock.presentation.components

import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.course.geststock.stock.data.DataStock
import com.course.geststock.stock.util.bitmapToByteArray

@Composable
fun ItemRegister(item: (DataStock) -> Unit = {}, onDismiss: () -> Unit = {}) {
    var type by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var quantity by remember { mutableStateOf("") }
    var imageBitmap by remember { mutableStateOf<Bitmap?>(null) }
    var validationAttempted by remember { mutableStateOf(false) }

    val context = LocalContext.current

    // Launcher para selecionar imagem
    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            if (uri != null) {
                val bitmap = MediaStore.Images.Media.getBitmap(context.contentResolver, uri)
                imageBitmap = bitmap
            }
        }
    )

    Dialog(onDismissRequest = { onDismiss() }) {
        Surface(
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.padding(16.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(text = "Adicionar Item", style = MaterialTheme.typography.bodyMedium)

                OutlinedTextField(
                    value = type,
                    onValueChange = { type = it },
                    label = { Text("Tipo") },
                    isError = validationAttempted && type.isBlank()
                )
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Nome") },
                    isError = validationAttempted && name.isBlank()
                )
                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("Descrição") },
                    isError = validationAttempted && description.isBlank()
                )
                OutlinedTextField(
                    value = price,
                    onValueChange = { price = it },
                    label = { Text("Preço") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    isError = validationAttempted && price.toDoubleOrNull() == null
                )
                OutlinedTextField(
                    value = quantity,
                    onValueChange = { quantity = it },
                    label = { Text("Quantidade") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    isError = validationAttempted && quantity.toDoubleOrNull() == null
                )

                // Botão para selecionar a imagem
                Button(onClick = { galleryLauncher.launch("image/*") }) {
                    Text("Selecionar Imagem")
                }
                imageBitmap?.let {
                    Text("Imagem selecionada", style = MaterialTheme.typography.bodySmall)
                }

                // Exibe mensagem de erro se a imagem não for selecionada
                if (validationAttempted && imageBitmap == null) {
                    Text(
                        text = "Por favor, selecione uma imagem.",
                        color = Color.Red,
                        style = MaterialTheme.typography.bodySmall
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(onClick = { onDismiss() }) {
                        Text("Cancelar")
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    TextButton(
                        onClick = {
                            validationAttempted = true
                            if (type.isNotBlank() &&
                                name.isNotBlank() &&
                                description.isNotBlank() &&
                                price.toDoubleOrNull() != null &&
                                quantity.toDoubleOrNull() != null &&
                                imageBitmap != null
                            ) {
                                val newItem = DataStock(
                                    type = type,
                                    name = name,
                                    description = description,
                                    price = price.toDoubleOrNull() ?: 0.0,
                                    quantity = quantity.toDoubleOrNull() ?: 0.0,
                                    image = bitmapToByteArray(imageBitmap!!)
                                )
                                item(newItem)
                                onDismiss()
                            }
                        }
                    ) {
                        Text("Adicionar")
                    }
                }
            }
        }
    }
}


@Preview
@Composable
fun PreviewItemRegister(){
    ItemRegister()
}