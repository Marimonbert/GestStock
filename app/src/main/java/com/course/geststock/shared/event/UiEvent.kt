package com.course.geststock.shared.event

import com.course.geststock.shared.state.AppBarState
import com.course.geststock.stock.data.DataStock


sealed class UiEvent : Event() {
    data class UpdateAppBarState(val newState: AppBarState) : UiEvent()
    data class Navigate(val route: String) : UiEvent()
    data class InsertStock(val item: DataStock) : UiEvent()
    data class DeleteStock(val item: DataStock) : UiEvent()
    data class AddToCart(val item: DataStock) : UiEvent()
    data class UpdateItem(val item: DataStock, val quantity : Double) : UiEvent()


    data class RemoveFromCart(val item: DataStock) : UiEvent()
    data object CloseSale : UiEvent()
    data object NavigateBack : UiEvent()
}
