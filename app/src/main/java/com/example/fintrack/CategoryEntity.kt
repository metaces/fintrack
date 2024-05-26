package com.example.fintrack

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categories")
data class CategoryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "key")
    val name:String,
    @ColumnInfo(name = "color")
    val color: String,
    @ColumnInfo(name = "total")
    val total: Float,
    @ColumnInfo(name = "icon")
    val icon: Int
)
