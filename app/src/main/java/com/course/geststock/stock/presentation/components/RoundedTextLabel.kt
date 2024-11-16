package com.course.geststock.stock.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.course.geststock.theme.FontSizeSubtitle2

@Composable
fun RoundedTextLabel(
    modifier: Modifier,
    text: String,
    colorText: Color,
    colorBackground: Color,
    click: () -> Unit
) {
    Box(
        modifier = modifier
            .background(color = colorBackground, shape = RoundedCornerShape(50))
            .padding(horizontal = 4.dp, vertical = 4.dp)
            .clickable { click() }
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center).wrapContentSize().padding(horizontal = 16.dp),
            text = text,
            color = colorText,
            fontSize = FontSizeSubtitle2,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}
