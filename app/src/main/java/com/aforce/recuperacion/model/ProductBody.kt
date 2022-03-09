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
    val regularPrice: Number,
    @Expose
    val discountPrice: Number,
    @Expose
    val available: Boolean,
    @Expose
    val imageUrl: String
)