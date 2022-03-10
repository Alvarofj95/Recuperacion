package com.aforce.recuperacion.db

import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.aforce.recuperacion.model.Product

interface Dao {
    @Query("SELECT * FROM ProductDb")
    fun getAll(): List<Product>

    @Query("SELECT * FROM ProductDb WHERE id = :productId")
    fun findById(productId: Int): ProductDb

    @Insert
    fun insertProduct(productDb: ProductDb)

    @Update
    fun updateProduct(productDb: ProductDb)
}