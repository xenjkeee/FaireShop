package com.faire.faireshop

import com.faire.faireshop.data.model.PriceInfo
import com.faire.faireshop.data.model.ProductInfo
import com.faire.faireshop.data.network.model.Price
import com.faire.faireshop.data.network.model.Product

fun Product.toProductInfo() = ProductInfo(
    detailsText = detailsText,
    productImage = productImage,
    productName = productName,
    wholesalePrice = wholesalePrice.toPriceInfo(),
)

fun Price.toPriceInfo() = PriceInfo(
    price = price,
    currency = currency,
)