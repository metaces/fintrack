package com.example.fintrack

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database-fintrack"
        ).build()
    }

    private val categoryDao by lazy {
        db.categoryDao()
    }

    private val adapter = CategoryListAdapter()
    private var categories: List<Category> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rvCategory = findViewById<RecyclerView>(R.id.rv_list_category)
        val fabCategory = findViewById<FloatingActionButton>(R.id.fab_category)

        rvCategory.adapter = adapter
        rvCategory.layoutManager = LinearLayoutManager(this)

        GlobalScope.launch(Dispatchers.IO){
            getCategoriesDatabase()
        }

        fabCategory.setOnClickListener {
            openCreateCategoryActivity()
        }

    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_NEW_CATEGORY && resultCode == Activity.RESULT_OK) {
           data?.let {
               val name = it.getStringExtra("CATEGORY_NAME")
               val color = it.getStringExtra("CATEGORY_COLOR")
               val icon = it.getIntExtra("CATEGORY_ICON", R.drawable.ic_wifi)
               if (name != null && color != null) {
                  val newCategory = CategoryEntity(name, color, 0.0f, icon)
                   addCategoryDatabase(newCategory)
//                   adapter.notifyItemInserted(categories.lastIndex)
               }
           }
        }
    }

    private fun addCategoryDatabase(categoryEntity: CategoryEntity) {
        GlobalScope.launch(Dispatchers.IO) {
            categoryDao.insertCategory(categoryEntity)
            getCategoriesDatabase()
        }
    }

    private fun getCategoriesDatabase() {
        GlobalScope.launch(Dispatchers.IO) {
            val categoriesEntity = categoryDao.getAllCategories()
            categories = categoriesEntity.map {
                Category(
                    name = it.name,
                    color = it.color,
                    total = it.total,
                    icon = it.icon
                )
            }
            GlobalScope.launch(Dispatchers.Main) {
                adapter.submitList(categories)
            }

        }
    }


    private fun openCreateCategoryActivity() {
        val intent = Intent(this, NewCategoryActivity::class.java)
        startActivityForResult(intent, REQUEST_CODE_NEW_CATEGORY)
    }

    companion object {
        private const val REQUEST_CODE_NEW_CATEGORY = 1
    }
}