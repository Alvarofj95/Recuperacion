package com.aforce.recuperacion.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
data class ProductDb(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "id") val idApi: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "stock") val stock: Int,
    @ColumnInfo(name = "discountPrice") val discountPrice: Number,
    @ColumnInfo(name = "imageUrl") val imageUrl: String,
    @ColumnInfo(name = "like") var like: Boolean
)
