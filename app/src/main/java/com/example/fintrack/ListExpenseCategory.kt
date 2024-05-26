package com.example.fintrack

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ListExpenseCategory : AppCompatActivity() {

    private val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database-fintrack"
        ).build()
    }

    private val expenseDao by lazy {
        db.expenseDao()
    }

    private val categoryDao by lazy {
        db.categoryDao()
    }

    private var adapter = ExpenseListAdapter()
    private var expenses: List<Expense> = emptyList()
    private lateinit var categoryEntity: CategoryEntity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_expense_category)

        val btnCreateExpense = findViewById<FloatingActionButton>(R.id.fab_create_expense)
        val categoryId = intent.getIntExtra("ID_CATEGORY", 1)

        GlobalScope.launch(Dispatchers.IO){
            categoryEntity = getCategoryDatabase(categoryId)
            val tvNameCategory = findViewById<TextView>(R.id.tv_name_category)
            tvNameCategory.text = categoryEntity.name
        }

        val rvExpense = findViewById<RecyclerView>(R.id.rv_list_expense)
        rvExpense.adapter = adapter
        rvExpense.layoutManager = LinearLayoutManager(this)

        GlobalScope.launch(Dispatchers.IO){
            getExpensesCategory(categoryId)
        }

        btnCreateExpense.setOnClickListener {
            openCreateExpenseCategory()
        }

    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ListExpenseCategory.REQUEST_CODE_NEW_EXPENSE && resultCode == Activity.RESULT_OK) {
            data?.let {
                val categoryId = it.getIntExtra("ID_CATEGORY", 1)
                val name = it.getStringExtra("NAME_EXPENSE")
                val value = it.getFloatExtra("VALUE_EXPENSE", 0.0f)

                if (name != null) {
                    val newExpense = ExpenseEntity(category = categoryId, name = name, value = value)
                    addExpenseDatabase(newExpense)
                }
            }
        }
    }

    fun getCategoryDatabase(id:Int): CategoryEntity{
        return categoryDao.getCategory(id)
    }

    fun openCreateExpenseCategory() {
        val intent = Intent(this, NewExpenseCategory::class.java)
        intent.putExtra("ID_CATEGORY", categoryEntity.id)
        intent.putExtra("NAME_CATEGORY", categoryEntity.name)
        startActivityForResult(intent, REQUEST_CODE_NEW_EXPENSE)
    }

    fun addExpenseDatabase(newExpense: ExpenseEntity) {
        GlobalScope.launch(Dispatchers.IO) {
            val categoryId = intent.getIntExtra("ID_CATEGORY", 1)
            expenseDao.insertExpense(newExpense)
            var total = categoryDao.getTotalCategory(categoryId)
            total +=  newExpense.value
            categoryDao.updateTotalCategory(categoryId, total)
            getExpensesCategory(categoryId)
        }
    }


    private fun getExpensesCategory(id: Int) {
        GlobalScope.launch(Dispatchers.IO) {
            val expensesEntity = expenseDao.getExpensesByCategory(id)
            expenses = expensesEntity.map {
                Expense(
                    name = it.name,
                    value = it.value,
                    category = it.category
                )
            }
            GlobalScope.launch(Dispatchers.Main) {
                adapter.submitList(expenses)
            }

        }
    }

    companion object {
        private const val REQUEST_CODE_NEW_EXPENSE = 2
    }
}