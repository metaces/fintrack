package com.example.fintrack

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CategoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCategory(category: CategoryEntity)
    @Query("Select * from categories")
    fun getAllCategories(): List<CategoryEntity>

    @Query("Select * from categories where id = :id")
    fun getCategory(id: Int): CategoryEntity

    @Query("Select total from categories where id = :id")
    fun getTotalCategory(id: Int): Float

    @Query("Update categories set total = :total WHERE id = :id")
    fun updateTotalCategory(id: Int, total: Float): Unit

    @Query("Select SUM(total) from categories")
    fun getTotalOutcome(): Float
}