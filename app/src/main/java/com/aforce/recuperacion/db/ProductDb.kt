package com.aforce.recuperacion.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "Product")
data class ProductDb(
    @PrimaryKey(autoGenerate = false)

    @ColumnInfo(name = "idApi") val idApi: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "stock") val stock: Int,
    @ColumnInfo(name = "discountPrice") val discountPrice: Float,
    @ColumnInfo(name = "imageUrl") val imageUrl: String,
    @ColumnInfo(name = "like") var liked: Boolean
)