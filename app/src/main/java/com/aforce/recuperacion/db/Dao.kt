package com.aforce.recuperacion.db

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.aforce.recuperacion.model.Product

interface Dao {
    @Query("SELECT * FROM ProductDb")
    fun getAll(): List<ProductDb>

    @Query("SELECT * FROM ProductDb WHERE idApi = :productId")
    fun findById(productId: Int): ProductDb

    @Insert
    fun insertProduct(List<ProductDb>)

    @Update
    fun updateProduct(productDb: ProductDb)

    @Delete
    fun deleteProduct(productDb: ProductDb)
}