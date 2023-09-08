package com.faire.faireshop.ui.compose.productslist

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.faire.faireshop.data.FaireShopRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class ProductsListViewModel @Inject constructor(
    //private val savedStateHandle: SavedStateHandle //nothing to restore so far
    private val repository: FaireShopRepository
) : ViewModel() {
    private val mutableState: MutableStateFlow<ProductsListState> =
        MutableStateFlow(ProductsListState.Loading)
    val state = mutableState.asStateFlow()

    fun handleAction(action: ProductsListAction) = when (action) {
        ProductsListAction.Reload -> loadProducts()
    }

    init {
        loadProducts()
    }

    private fun loadProducts() = flow {
        emit(repository.getProducts())
    }.onStart {
        Log.d(this::class.simpleName, "Loading started")
        mutableState.value = ProductsListState.Loading
    }.catch {
        Log.d(this::class.simpleName, "Loading failed")
        mutableState.value = ProductsListState.LoadingFailed
    }.onEach { list ->
        Log.d(this::class.simpleName, "Loading finished; products = [$list]")
        mutableState.value = when (list.size) {
            0 -> ProductsListState.Empty
            else -> ProductsListState.Success(list)
        }
    }.launchIn(viewModelScope)
}

