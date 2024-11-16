package com.course.geststock.stock.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.course.geststock.R
import com.course.geststock.stock.data.DataStock
import com.course.geststock.theme.DarkBlue
import com.course.geststock.theme.FontSizeSubtitle2
import com.course.geststock.theme.Green
import com.course.geststock.theme.Green1
import com.course.geststock.theme.RedDark

@Composable
fun ShopItem(
    index: Int,
    item: DataStock,
    quantities: Double,
    backgroundColor: Color,
    onRemove: () -> Unit,
    onEdit: () -> Unit
) {
    // Calcula o preço total com base na quantidade, validando se é maior que 0
    val totalPrice = if (quantities > 0) item.price * quantities else 0.0

    Surface(
        color = Color.Transparent,
        shape = RoundedCornerShape(10.dp),
    ) {
        Column(modifier = Modifier.padding(4.dp)) { // Padding interno para o conteúdo
            Row(
                modifier = Modifier
                    .wrapContentWidth()
                    .height(30.dp)
            ) {
                Text(
                    text = "# ${item.id} - ${item.description}",
                    fontWeight = FontWeight.Bold,
                    color = DarkBlue,
                    fontSize = 14.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .weight(1f)
                        .padding(start = 20.dp)
                )

                ImageButton(
                    modifier = Modifier
                        .fillMaxHeight()
                        .size(28.dp)
                        .padding(start = 5.dp)
                        .background(Color.Transparent),
                    imageId = R.drawable.ic_bin,
                    color = RedDark,
                ) {
                    onRemove()
                }
            }

            Row {
                Text(
                    text = index.toString(),
                    fontWeight = FontWeight.Bold,
                    color = DarkBlue,
                    fontSize = 14.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .weight(1f)
                        .padding(start = 5.dp)
                )

                CustomCard(
                    modifier = Modifier
                        .wrapContentSize()
                        .size(70.dp)
                        .padding(start = 6.dp),
                ) {
                    ProductImage(imageData = item.image)
                }

                Column(
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(start = 10.dp)
                ) {

                    Row(
                        modifier = Modifier
                            .padding(bottom = 8.dp)
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = "Quantidade:",
                            fontWeight = FontWeight.SemiBold,
                            color = DarkBlue,
                            fontSize = FontSizeSubtitle2,
                            modifier = Modifier.align(alignment = Alignment.CenterVertically)
                        )

                        RoundedTextLabel(
                            modifier = Modifier
                                .padding(start = 20.dp)
                                .wrapContentSize(),
                            text = "x$quantities",
                            colorText = Color.White,
                            colorBackground = DarkBlue,
                            click = {
                                // Ação para a quantidade
                            }
                        )

                        // Spacer to push the edit icon to the end
                        Spacer(modifier = Modifier.weight(1f))

                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = "Editar",
                            tint = Green1,
                            modifier = Modifier
                                .size(24.dp)
                                .align(Alignment.CenterVertically) // Align vertically
                                .clickable { onEdit() }
                        )
                    }
                    Row(
                        modifier = Modifier
                            .padding(bottom = 8.dp)
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = "Preço Total:",
                            fontWeight = FontWeight.SemiBold,
                            color = DarkBlue,
                            fontSize = FontSizeSubtitle2,
                            modifier = Modifier.align(alignment = Alignment.CenterVertically)
                        )

                        Text(
                            text = "R$ ${"%.2f".format(totalPrice)}",
                            fontWeight = FontWeight.Bold,
                            color = Green,
                            fontSize = 14.sp,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .weight(1f)
                                .padding(start = 5.dp)
                        )
                    }
                }
            }

            // Adicionando o HorizontalDivider
            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                color = Color.LightGray,
                thickness = 1.dp
            )
        }
    }
}
