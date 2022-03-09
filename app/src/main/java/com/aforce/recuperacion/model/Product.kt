package com.aforce.recuperacion.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("id")
    @Expose
    val id: String,
    @SerializedName("name")
    @Expose
    val name: String,
    @SerializedName("description")
    @Expose
    val description: String,
    @SerializedName("stock")
    @Expose
    val stock: Int,
    @SerializedName("regularPrice")
    @Expose
    val regularPrice: Number,
    @SerializedName("discountPrice")
    @Expose
    val discountPrice: Number,
    @SerializedName("available")
    @Expose
    val available: Boolean,
    @SerializedName("imageUrl")
    @Expose
    val imageUrl: String
)