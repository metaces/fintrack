package com.example.fintrack

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ExpenseDao {

    @Insert
    fun insertExpense(expenseEntity: ExpenseEntity)

    @Query("Select * from expenses")
    fun getAllExpenses(): List<ExpenseEntity>

    @Query("Select * from expenses where category = :id")
    fun getExpensesByCategory(id: Int): List<ExpenseEntity>
}