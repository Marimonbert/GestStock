package com.course.geststock.stock.data

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "stock.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "stock"

        private const val COLUMN_ID = "id"
        private const val COLUMN_TYPE = "type"
        private const val COLUMN_NAME = "name"
        private const val COLUMN_DESCRIPTION = "description"
        private const val COLUMN_PRICE = "price"
        private const val COLUMN_QUANTITY = "quantity"
        private const val COLUMN_IMAGE = "image"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = ("CREATE TABLE $TABLE_NAME (" +
                "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$COLUMN_TYPE TEXT," +
                "$COLUMN_NAME TEXT," +
                "$COLUMN_DESCRIPTION TEXT," +
                "$COLUMN_PRICE REAL," +
                "$COLUMN_QUANTITY REAL," +
                "$COLUMN_IMAGE BLOB)")
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    // Função para inserir um item
    fun addItem(item: DataStock): Long {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_TYPE, item.type)
            put(COLUMN_NAME, item.name)
            put(COLUMN_DESCRIPTION, item.description)
            put(COLUMN_PRICE, item.price)
            put(COLUMN_QUANTITY, item.quantity)
            put(COLUMN_IMAGE, item.image)
        }
        val success = db.insert(TABLE_NAME, null, values)
        db.close()
        return success
    }

    // Função para listar itens
    fun listItems(): List<DataStock> {
        val itemList = mutableListOf<DataStock>()
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_NAME", null)
        if (cursor.moveToFirst()) {
            do {
                val item = DataStock(
                    id = cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_ID)),
                    type = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TYPE)),
                    name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME)),
                    description = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DESCRIPTION)),
                    price = cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_PRICE)),
                    quantity = cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_QUANTITY)),
                    image = cursor.getBlob(cursor.getColumnIndexOrThrow(COLUMN_IMAGE))
                )
                itemList.add(item)
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return itemList
    }

    // Função para remover item
    fun removeItem(id: Long): Boolean {
        val db = this.writableDatabase
        val success = db.delete(TABLE_NAME, "$COLUMN_ID=?", arrayOf(id.toString())) > 0
        db.close()
        return success
    }
}
