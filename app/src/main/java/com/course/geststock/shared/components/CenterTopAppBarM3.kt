package com.course.geststock.shared.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.course.geststock.stock.presentation.components.BadgeM3
import com.course.geststock.theme.DarkBlue
import com.course.geststock.theme.Green
import com.course.geststock.theme.Pink40

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CenterTopAppBarM3(
    title: String,
    onCartClick: () -> Unit,
    backClick: () -> Unit,
    buttonBack: Boolean,
    item: Int
) {

    CenterAlignedTopAppBar(
        modifier = Modifier.fillMaxWidth(),
        colors = TopAppBarDefaults.topAppBarColors(containerColor = DarkBlue),
        title = { Text(text = title, color = Color.White, fontSize = 18.sp) },
        navigationIcon = {

            if (buttonBack) {

                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.White,
                    modifier = Modifier.clickable { backClick() }
                )

            }
        },
        actions = {

            BadgeM3(item, onCartClick)

        }
    )
}