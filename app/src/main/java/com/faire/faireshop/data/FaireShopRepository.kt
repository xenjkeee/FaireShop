package com.faire.faireshop.data

import com.faire.faireshop.data.network.NetworkDataSource
import javax.inject.Inject

class FaireShopRepository @Inject constructor(
    private val remoteDataSource: NetworkDataSource
) {
    suspend fun getProducts() = remoteDataSource.getProducts()
}