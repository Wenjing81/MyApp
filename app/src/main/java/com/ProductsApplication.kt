package com

import android.app.Application

class ProductsApplication : Application() {

    val database by lazy { AppDatabase.getDatabase(this) }
    val repository by lazy { ProductItemRepository(database.itemDao()) }
    val productViewModel by lazy { ProductViewModel(repository) }
}


