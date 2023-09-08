package com.faire.faireshop.data.network.api

import com.faire.faireshop.data.network.model.Product
import retrofit2.http.GET

interface FaireShopService {
    @GET("static/mobile-take-home/products-response.json")
    suspend fun getProducts(): Result<List<Product>>
}