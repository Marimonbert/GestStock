package com.course.geststock.stock.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.course.geststock.theme.DarkBlue
import com.course.geststock.theme.Green

@Composable
fun EmptyListItem(textColor: Color = DarkBlue) {

    Column(
        modifier = Modifier
            .padding(top = 20.dp)
            .fillMaxSize(),
    ) {
        Text(
            text = "Nenhum item adicionado.",
            fontSize = 16.sp,
            color = textColor,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.align(
                Alignment.CenterHorizontally
            )
        )

    }
}