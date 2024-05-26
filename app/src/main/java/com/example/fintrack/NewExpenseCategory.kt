package com.example.fintrack

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText

class NewExpenseCategory : AppCompatActivity() {

    private lateinit var expenseName: String
    private  var expenseValue: Float = 0.0f
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_expense_category)

        val categoryId = intent.getIntExtra("ID_CATEGORY", 1)
        val categoryName = intent.getStringExtra("NAME_CATEGORY")
        val tvNameCategory = findViewById<TextView>(R.id.tv_name_category)
        tvNameCategory.text = categoryName

        val tieName = findViewById<TextInputEditText>(R.id.tie_name)
        val tieValue = findViewById<TextInputEditText>(R.id.tie_value)
        val btnCreate = findViewById<Button>(R.id.btn_create_expense)


        btnCreate.setOnClickListener {
            expenseName = tieName.text.toString()
            expenseValue = tieValue.text.toString().toFloat()
            if(expenseName.isNotEmpty()){
                val resultIntent = Intent(this, MainActivity::class.java)
                resultIntent.putExtra("ID_CATEGORY", categoryId)
                resultIntent.putExtra("NAME_EXPENSE", expenseName)
                resultIntent.putExtra("VALUE_EXPENSE", expenseValue)
                setResult(Activity.RESULT_OK, resultIntent)
                finish()
            }
        }
    }
}