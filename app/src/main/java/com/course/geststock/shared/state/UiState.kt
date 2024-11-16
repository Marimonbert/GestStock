package com.course.geststock.shared.state

import com.course.geststock.stock.data.DataStock

data class UiState(
    val appBarConfig: AppBarState = AppBarState(),
    val floatingButton: Boolean = true,
    val stocks: List<DataStock> = emptyList(),
    val cartItems: Map<DataStock, Double> = emptyMap(),
)