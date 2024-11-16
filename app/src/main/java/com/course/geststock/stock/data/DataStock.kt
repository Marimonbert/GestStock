package com.course.geststock.stock.data


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "stock_table")
data class DataStock(
    val id: Long = 0,
    val type: String,
    val name: String,
    val description: String,
    val price: Double,
    val quantity: Double,
    val image: ByteArray
)

