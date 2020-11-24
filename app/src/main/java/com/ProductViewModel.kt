package com

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ProductViewModel(private val repository: ProductItemRepository) : ViewModel() {

    fun insert(productItem: ProductItem) = viewModelScope.launch {
        repository.insertItem(productItem)
    }

    fun getAll(): List<ProductItem> {
        return repository.getAll()
    }
    fun removeAll(){
        return repository.removeAll()
    }
}