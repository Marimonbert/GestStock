package com.course.geststock.stock.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.course.geststock.R
import com.course.geststock.stock.data.DataStock
import com.course.geststock.theme.DarkBlue
import com.course.geststock.theme.FontSizeBody2
import com.course.geststock.theme.Green
import com.course.geststock.theme.IconSizeSmall
import com.course.geststock.theme.IconSizeXXLarge
import com.course.geststock.theme.LightGray
import com.course.geststock.theme.LightGreen
import com.course.geststock.theme.Pink40
import com.course.geststock.theme.SpaceExtraSmall
import com.course.geststock.theme.SpaceLarge
import com.course.geststock.theme.SpaceMedium
import com.course.geststock.theme.SpaceSmall


@Composable
fun StockItem(
    item: DataStock,
    backgroundColor: Color,
    selectedQuantity: Double,
    onAddClick: (DataStock) -> Unit,
    onRemoveClick: (DataStock) -> Unit
) {
    CustomCard(
        modifier = Modifier
            .padding(start = 5.dp, end = 5.dp, top = 12.dp)
            .fillMaxWidth()
            .wrapContentHeight()
            .size(110.dp),
        backgroundColor = backgroundColor,
        elevation = 2.dp,
        shape = RoundedCornerShape(10.dp)
    ) {
        Row(
            modifier = Modifier
                .wrapContentWidth()
                .background(LightGray)
                .height(30.dp)
        ) {
            Text(
                text = "${item.id}  ${item.name}",
                fontWeight = FontWeight.Bold,
                color = Color.White,
                fontSize = 12.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .weight(1f)
                    .padding(start = 20.dp)
            )

            // Botão de remover
            ImageButton(
                modifier = Modifier
                    .fillMaxHeight(),
                imageId = R.drawable.ic_bin,
                color = Color.White,
                enabled = selectedQuantity > 0,
                onClick = {
                    onRemoveClick(item)
                }
            )
            ImageButton(
                modifier = Modifier
                    .fillMaxHeight()
                    .background(DarkBlue),
                imageId = R.drawable.ic_add,
                color = Color.White,
                enabled = selectedQuantity < item.quantity,
                onClick = {
                    onAddClick(item)
                }
            )


        }

        Row(
            modifier = Modifier
                .fillMaxHeight()
                .background(color = Color.White)
        ) {
            Row(
                modifier = Modifier
                    .size(IconSizeXXLarge, IconSizeXXLarge)
                    .align(alignment = Alignment.CenterVertically)
                    .padding(start = SpaceMedium)
            ) {
                ProductImage(imageData = item.image)
            }
            VerticalDivider(
                color = DarkBlue,
                modifier = Modifier
                    .wrapContentHeight()
                    .padding(start = SpaceSmall)
            )

            Column(
                modifier = Modifier
                    .padding(start = SpaceSmall)
                    .weight(1f)
            ) {
                Text(text = item.description)
                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Preço: ${item.price}",
                    color = Green,
                    fontWeight = FontWeight.Bold
                )

                // Exibe quantidade em estoque e quantidade selecionada
                Text(
                    text = "Estoque: ${item.quantity.toInt()}",
                    color = if (selectedQuantity >= item.quantity) Color.Red else Color.Gray,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
