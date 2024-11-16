package com.course.geststock.stock.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun ImageButton(
    modifier: Modifier = Modifier,
    imageId: Int,
    color: Color,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .size(35.dp)
            .clickable(enabled = enabled) { onClick() }, // Controla o clique com `enabled`
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Icon(
            modifier = Modifier
                .size(25.dp)
                .align(Alignment.CenterVertically).clickable {onClick()  },
            painter = painterResource(id = imageId),
            contentDescription = null, // Geralmente melhor deixar null para ícones de controle
            tint = if (enabled) color else color.copy(alpha = 0.5f) // Ícone mais opaco se desabilitado
        )
    }
}
