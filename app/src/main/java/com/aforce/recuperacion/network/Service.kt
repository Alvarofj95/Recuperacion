package com.aforce.recuperacion.network

import com.aforce.recuperacion.model.Product
import com.aforce.recuperacion.model.ProductBody
import retrofit2.Call
import retrofit2.http.*

interface Service {

    @GET("product")
    fun getProduct(): Call<List<Product>>

    @GET("product/{id}")
    fun getProductById(@Path("id") productId: String): Call<Product>

    @POST("product")
    fun createProduct(@Body product: ProductBody): Call<Any>

    @DELETE("product/{id}")
    fun deleteProductById(@Path("id") productId: String): Call<Void>

}