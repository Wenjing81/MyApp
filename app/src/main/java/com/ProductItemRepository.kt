package com

class ProductItemRepository(private val productDao: ProductItemDao) {
    fun insertItem(item: ProductItem) {
        productDao.insert(item)
    }

    fun getAll(): List<ProductItem> {
        return productDao.getAll()
    }
    fun removeAll(){
        return productDao.deleteAll()
    }
}