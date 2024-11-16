package com.course.geststock

import android.app.Application
import com.course.geststock.stock.data.DatabaseHelper
import com.course.geststock.stock.repository.StockRepository

class MyApplication : Application() {


    private val databaseHelper: DatabaseHelper by lazy {
        DatabaseHelper(this)
    }

    val repository: StockRepository by lazy {
        StockRepository(databaseHelper)
    }
}
