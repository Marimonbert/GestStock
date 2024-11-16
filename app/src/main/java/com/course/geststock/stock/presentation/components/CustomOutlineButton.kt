package com.course.geststock.stock.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.course.geststock.theme.Green
import com.course.geststock.theme.LightGray

@Composable
fun CustomOutlineButton(
    onClick: () -> Unit,
    title: String,
    modifier: Modifier = Modifier,
    subtitle: String?, // Subtítulo é a descrição selecionada (família ou subfamília)
    icon: ImageVector? = null,  // O ícone é opcional
    isSelected: Boolean = false, // Verifica se o item está selecionado
    buttonColor: Color = Green  // Cor padrão, pode ser modificada
) {
    val selectedColor = if (isSelected) Green else buttonColor // Muda a cor para azul se o item estiver selecionado

    OutlinedButton(
        onClick = { onClick() },
        colors = ButtonDefaults.outlinedButtonColors(contentColor = selectedColor),
        border = BorderStroke(1.5.dp, selectedColor),
        shape = RoundedCornerShape(30.dp),
        modifier = modifier
            .padding(horizontal = 8.dp) // Controla o padding horizontal
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth() // Garante que ocupe a largura disponível
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)

            ) {
                Text(
                    text = title,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 1, // Garante uma única linha
                    overflow = TextOverflow.Ellipsis, // Trunca com "..."
                    modifier = Modifier.align(Alignment.CenterHorizontally) // Centraliza o título
                )

                if (subtitle != null) {
                    Text(
                        color = LightGray,
                        text = subtitle,
                        fontSize = 10.sp, // Subtítulo um pouco menor
                        fontWeight = FontWeight.Normal,
                        maxLines = 1, // Garante que o subtítulo também tenha uma única linha
                        overflow = TextOverflow.Ellipsis, // Trunca com "..."
                        modifier = Modifier.align(Alignment.CenterHorizontally) // Centraliza o subtítulo
                    )
                }
            }

            if (icon != null) {
                Icon(
                    imageVector = icon,
                    contentDescription = "Icon",
                    modifier = Modifier
                        .size(22.dp) // Tamanho do ícone
                        .align(Alignment.CenterVertically)
                )
            }
        }
    }
}
