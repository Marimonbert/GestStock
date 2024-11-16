package com.course.geststock.stock.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.course.geststock.R
import com.course.geststock.stock.util.byteArrayToBitmap

@Composable
fun ProductImage(imageData: ByteArray?) {
    // Placeholder padrão para quando não houver imagem
    val placeholderImage = painterResource(id = R.drawable.ic_box)

    // Converter ByteArray em Bitmap (se disponível)
    val bitmap = imageData?.let { byteArrayToBitmap(it) }

    if (bitmap != null) {
        // Exibe a imagem convertida
        Image(
            bitmap = bitmap.asImageBitmap(),
            contentDescription = "Product Image",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .size(80.dp)
                .padding(4.dp)
        )
    } else {
        // Exibe o placeholder se não houver imagem
        Image(
            painter = placeholderImage,
            contentDescription = "Placeholder Image",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .size(50.dp)
                .padding(4.dp)
        )
    }
}
