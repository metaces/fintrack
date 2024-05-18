package com.example.fintrack

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rvCategory = findViewById<RecyclerView>(R.id.rv_list_category)
        val fabCategory = findViewById<FloatingActionButton>(R.id.fab_category)

        val adapter = CategoryListAdapter()
        rvCategory.adapter = adapter
        rvCategory.layoutManager = LinearLayoutManager(this)

        adapter.submitList(categories)

        fabCategory.setOnClickListener {
            openCreateCategoryActivity()
        }



    }

    private fun openCreateCategoryActivity() {
        val intent = Intent(this, NewCategoryActivity::class.java)
        startActivity(intent)
    }
}

val categories = listOf(
    Category(
        name = "Wifi",
        color = "#000000",
        total = 152.25f,
        R.drawable.ic_wifi,
    ),
    Category(
        name = "Energy bill",
        color = "#000000",
        total = 252.25f,
        R.drawable.ic_electricity
    ),
    Category(
        name = "Gas station",
        color = "#000000",
        total = 352.25f,
        R.drawable.ic_gas_station
    ),
    Category(
        name = "Clothes",
        color = "#000000",
        total = 52.25f,
        R.drawable.ic_clothes
    )
    ,Category(
        name = "Rent",
        color = "#000000",
        total = 852.25f,
        R.drawable.ic_home
    ),
    Category(
        name = "Uber",
        color = "#000000",
        total = 12.59f,
        R.drawable.ic_car
    ),
    Category(
        name = "CreditCard",
        color = "#000000",
        total = 122.59f,
        R.drawable.ic_credit_card
    ),
    Category(
        name = "Clothes",
        color = "#000000",
        total = 52.25f,
        R.drawable.ic_clothes
    )
    ,Category(
        name = "Rent",
        color = "#000000",
        total = 852.25f,
        R.drawable.ic_home
    ),
    Category(
        name = "Uber",
        color = "#000000",
        total = 12.59f,
        R.drawable.ic_car
    ),
    Category(
        name = "CreditCard",
        color = "#000000",
        total = 122.59f,
        R.drawable.ic_credit_card
    ),
)