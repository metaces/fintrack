package com.example.fintrack

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: CategoryListAdapter
    private val categories = mutableListOf(
            Category(
                name = "Wifi",
                color = "#F54336",
                total = 152.25f,
                com.example.fintrack.R.drawable.ic_wifi,
            )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rvCategory = findViewById<RecyclerView>(R.id.rv_list_category)
        val fabCategory = findViewById<FloatingActionButton>(R.id.fab_category)

        adapter = CategoryListAdapter()
        rvCategory.adapter = adapter
        rvCategory.layoutManager = LinearLayoutManager(this)

        adapter.submitList(categories)

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
                  val newCategory = Category(name, color, 0.0f, icon)
                   categories.add(newCategory)
                   adapter.notifyItemInserted(categories.lastIndex)
               }
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