package com.faire.faireshop.data.model

data class ProductInfo(
    val detailsText: String?,
    val productImage: String?,
    val productName: String,
    val wholesalePrice: PriceInfo,
)