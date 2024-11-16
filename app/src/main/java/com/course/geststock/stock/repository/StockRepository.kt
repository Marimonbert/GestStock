package com.course.geststock.stock.repository

import android.content.ContentValues
import com.course.geststock.stock.data.DataStock
import com.course.geststock.stock.data.DatabaseHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class StockRepository(private val databaseHelper: DatabaseHelper) {

    fun getAllStocks(): List<DataStock> {
        return databaseHelper.listItems()
    }


    suspend fun insert(stock: DataStock) {
        withContext(Dispatchers.IO) {
            databaseHelper.addItem(stock)
        }
    }

    suspend fun delete(stock: DataStock) {
        withContext(Dispatchers.IO) {
            databaseHelper.removeItem(stock.id)
        }
    }

    suspend fun updateStocks(stocks: List<DataStock>) {
        val db = databaseHelper.writableDatabase
        db.beginTransaction()
        try {
            for (stock in stocks) {
                val values = ContentValues().apply {
                    put("quantity", stock.quantity)
                }
                db.update(
                    "stock",
                    values,
                    "id = ?",
                    arrayOf(stock.id.toString())
                )
            }
            db.setTransactionSuccessful()
        } finally {
            db.endTransaction()
        }
    }

}
