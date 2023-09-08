package com.faire.faireshop.ui.compose.productslist

sealed class ProductsListAction {
    object Reload : ProductsListAction()
    //data class Click(val product: ProductInfo): ProductsListAction()
}