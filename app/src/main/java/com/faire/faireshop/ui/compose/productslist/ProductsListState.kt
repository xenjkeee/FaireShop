package com.faire.faireshop.ui.compose.productslist

import com.faire.faireshop.data.model.ProductInfo

sealed class ProductsListState {
    object Loading : ProductsListState()
    object LoadingFailed : ProductsListState()
    object Empty : ProductsListState()
    data class Success(val products: List<ProductInfo>) : ProductsListState()
}