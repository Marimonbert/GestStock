package com.course.geststock.domain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.course.geststock.stock.presentation.StockViewModel
import com.course.geststock.stock.repository.StockRepository

class StockViewModelFactory(private val repository: StockRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StockViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return StockViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
