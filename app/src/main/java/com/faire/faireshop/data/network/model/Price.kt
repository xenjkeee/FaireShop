package com.faire.faireshop.data.network.model

import com.google.gson.annotations.SerializedName

data class Price(
    @SerializedName("price") val price: Double,
    @SerializedName("currency") val currency: String,
)