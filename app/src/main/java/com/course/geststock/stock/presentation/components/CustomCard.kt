package com.course.geststock.stock.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


@Composable
fun CustomCard(
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(12),
    backgroundColor: Color = Color.White,
    contentColor: Color = Color.Black,
    elevation: Dp = 8.dp,
    border: BorderStroke? = null,
    content: @Composable ColumnScope.() -> Unit
) {
    Surface(
        modifier = modifier,
        shape = shape,
        color = backgroundColor,
        contentColor = contentColor,
        shadowElevation = elevation,
        border = border,
    ) {
        Column(content = content)
    }
}

