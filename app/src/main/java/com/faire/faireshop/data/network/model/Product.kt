package com.faire.faireshop.data.network.model

import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("detailsText") val detailsText: String?,
    @SerializedName("productImage") val productImage: String?,
    @SerializedName("productName") val productName: String,
    @SerializedName("wholesalePrice") val wholesalePrice: Price,
)