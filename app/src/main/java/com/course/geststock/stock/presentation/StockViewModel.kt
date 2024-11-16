package com.course.geststock.stock.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
import com.course.geststock.shared.event.UiEvent
import com.course.geststock.shared.event.emitUiEvent
import com.course.geststock.shared.state.UiState
import com.course.geststock.stock.data.DataStock
import com.course.geststock.stock.repository.StockRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class StockViewModel(private val repository: StockRepository) : ViewModel() {

    private val _state = mutableStateOf(UiState())
    val state: State<UiState> = _state

    private val _eventFlow = Channel<UiEvent>()
    val eventFlow = _eventFlow.receiveAsFlow()

    init {
        loadStocks() // Carrega a lista de estoque ao iniciar o ViewModel
    }

    private fun loadStocks() {
        viewModelScope.launch {
            val stocks = repository.getAllStocks()
            _state.value = state.value.copy(stocks = stocks)
        }
    }

    // Função para adicionar um item ao carrinho
    private fun addToCart(item: DataStock) {
        val updatedCart = _state.value.cartItems.toMutableMap()

        // Incrementa a quantidade ou insere o item com quantidade 1
        val currentQuantity = updatedCart[item] ?: 0.0
        updatedCart[item] = currentQuantity + 1.0

        _state.value = state.value.copy(cartItems = updatedCart)
    }

    // Função para remover um item do carrinho
    private fun removeFromCart(item: DataStock) {
        val updatedCart = _state.value.cartItems.toMutableMap()

        val currentQuantity = updatedCart[item] ?: 0.0
        if (currentQuantity > 1) {
            // Decrementa a quantidade
            updatedCart[item] = currentQuantity - 1.0
        } else {
            // Remove o item se a quantidade for 1 ou menos
            updatedCart.remove(item)
        }

        _state.value = state.value.copy(cartItems = updatedCart)
    }

    // Função para processar eventos
    fun onEvent(event: UiEvent) {
        viewModelScope.launch {
            when (event) {
                is UiEvent.InsertStock -> insertStock(event.item)
                is UiEvent.DeleteStock -> deleteStock(event.item)
                is UiEvent.UpdateAppBarState -> _state.value =
                    state.value.copy(appBarConfig = event.newState)

                is UiEvent.Navigate -> _eventFlow.send(event)
                UiEvent.NavigateBack -> _eventFlow.send(UiEvent.NavigateBack)
                is UiEvent.AddToCart -> addToCart(event.item)
                is UiEvent.RemoveFromCart -> removeFromCart(event.item)
                is UiEvent.CloseSale -> closeSale()
                is UiEvent.UpdateItem -> updateCartItem(event.item, event.quantity)
            }
        }
    }
    private fun closeSale() {
        viewModelScope.launch {
            val updatedStocks = _state.value.stocks.map { stock ->
                val cartQuantity = _state.value.cartItems[stock] ?: 0.0
                if (cartQuantity > 0) {
                    stock.copy(quantity = stock.quantity - cartQuantity)
                } else {
                    stock
                }
            }

            // Atualiza o estoque no banco de dados
            repository.updateStocks(updatedStocks)
            // Limpa o carrinho
            _state.value = state.value.copy(cartItems = emptyMap())
            // Recarrega os estoques
            loadStocks()
            _eventFlow.send(UiEvent.NavigateBack)

        }
    }
    private suspend fun insertStock(stock: DataStock) {
        repository.insert(stock)
        loadStocks()
    }

    private suspend fun deleteStock(stock: DataStock) {
        repository.delete(stock)
        loadStocks()
    }
    private fun updateCartItem(item: DataStock, newQuantity: Double) {
        val updatedCart = _state.value.cartItems.toMutableMap()

        if (newQuantity <= 0) {
            // Remove o item do carrinho se a nova quantidade for zero ou menor
            updatedCart.remove(item)
        } else if (newQuantity <= item.quantity) {
            // Atualiza a quantidade no carrinho se for válida
            updatedCart[item] = newQuantity
        }

        // Atualiza o estado com o carrinho modificado
        _state.value = state.value.copy(cartItems = updatedCart)
    }

}
