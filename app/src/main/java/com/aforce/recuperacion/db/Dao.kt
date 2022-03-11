package com.aforce.recuperacion.db

import androidx.room.*
import androidx.room.Dao
import com.aforce.recuperacion.model.Product
@Dao
interface Dao {
    @Query("SELECT * FROM Product")
    fun getAll(): List<ProductDb>

    @Query("SELECT * FROM Product WHERE Product.like = true ")
    fun findByFav(): List<ProductDb>

    @Query("SELECT * FROM Product WHERE Product.idApi = :productDb ")
    fun findById(productDb: Int): List<ProductDb>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProduct(productDb: ProductDb)

    @Update
    fun updateProduct(productDb: ProductDb)

    @Delete
    fun deleteProduct(productDb: ProductDb)
}