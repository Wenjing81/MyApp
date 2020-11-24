package com

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "ProductItem")

data class ProductItem(
    @ColumnInfo
    var productNumber: Int = 0,
    @ColumnInfo
    var productImage: Int = 0,
    @ColumnInfo
    var productType: String = "",
    @ColumnInfo
    var productPrice: Int = 0,
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo
    var id: Long = 0L
)