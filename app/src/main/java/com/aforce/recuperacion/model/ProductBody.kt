package com.aforce.recuperacion.model

import com.google.gson.annotations.Expose

data class ProductBody(
    @Expose
    val name: String,
    @Expose
    val description: String,
    @Expose
    val stock: Int,
    @Expose
    val regularPrice: Float,
    @Expose
    val discountPrice: Float,
    @Expose
    val imageUrl: String,
    @Expose
    val available: Boolean
)