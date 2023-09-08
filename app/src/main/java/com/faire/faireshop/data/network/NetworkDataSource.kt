package com.faire.faireshop.data.network

import com.faire.faireshop.data.network.api.FaireShopService
import com.faire.faireshop.toProductInfo
import javax.inject.Inject

class NetworkDataSource @Inject constructor(
    private val api: FaireShopService
) {
    suspend fun getProducts() = api.getProducts().fold(
        onSuccess = { list -> list.map { it.toProductInfo() } },
        onFailure = { throw it }
    )
}