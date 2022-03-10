package com.aforce.recuperacion.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import java.security.AccessControlContext


@Database(entities = [ProductDb::class], version = 1)

abstract class DatabaseProduct : RoomDatabase() {
    abstract fun dao(): Dao

    companion object {
        @Volatile
        private var INSTANCE: DatabaseProduct? = null
        fun getInstance(context: Context): DatabaseProduct = INSTANCE ?: synchronized(this) {
            INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext, DatabaseProduct::class.java, "product.db").allowMainThreadQueries().build()
    }
}