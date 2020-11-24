package com

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query


@Dao
interface ProductItemDao {
    @Insert
    fun insert(item: ProductItem)

    @Delete
    fun delete(item: ProductItem)

    @Query("SELECT*FROM ProductItem")
    fun getAll(): List<ProductItem>

    @Query("SELECT*FROM ProductItem WHERE productType LIKE:productType")
    fun findByProductType(productType: String): List<ProductItem>

    @Query("DELETE FROM ProductItem")
    fun deleteAll()


}