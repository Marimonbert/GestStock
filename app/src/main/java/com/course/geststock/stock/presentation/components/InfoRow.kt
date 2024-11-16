package com.course.geststock.stock.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import com.course.geststock.theme.FontSizeBody1
import com.course.geststock.theme.IconSizeDefault
import com.course.geststock.theme.SpaceLarge
import com.course.geststock.theme.SpaceMedium
import com.course.geststock.theme.SpaceSmall

@Composable
fun InfoRow(
    icon: Painter,
    text: String,
    contentColor: Color,
    iconSize: Dp = IconSizeDefault,
    fontSize: TextUnit = FontSizeBody1,
    backgroundColor: Color = Color.Transparent,
    cornerRadius: Dp = SpaceLarge,
    padding: Dp = SpaceMedium,
) {

    Column(
        Modifier
            .wrapContentSize()
            .background(
                backgroundColor,
                shape = RoundedCornerShape(cornerRadius)
            )
        ,
        verticalArrangement = Arrangement.Top,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = icon,
                contentDescription = text,
                modifier = Modifier.size(iconSize),
                tint = contentColor
            )
            Spacer(modifier = Modifier.width(SpaceSmall))
            Text(
                text = text,
                style = TextStyle(fontSize = fontSize, color = contentColor),
                modifier = Modifier.align(Alignment.CenterVertically)
            )

            Spacer(modifier = Modifier.width(SpaceSmall))
        }
    }
}
