package com.course.geststock.stock.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.course.geststock.theme.IconSizeDefault

@Composable
fun BadgeM3(count: Int, onAddClick: () -> Unit) {
    BadgedBox(
        badge = {
            if (count > 0) {
                Badge { Text(text = count.toString()) }
            }
        }
    ) {
        Icon(
            imageVector = Icons.Default.ShoppingCart,
            contentDescription = "Favorite",
            tint = Color.White,
            modifier = Modifier
                .padding(end = 10.dp)
                .size(IconSizeDefault)
                .clickable(onClick = onAddClick)
        )
    }
}
