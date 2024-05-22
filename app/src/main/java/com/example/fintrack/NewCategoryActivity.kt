package com.example.fintrack

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText

class NewCategoryActivity : AppCompatActivity() {

    private lateinit var selectedColorName: String
    private lateinit var categoryName: String
    private var selectedIcon: Int = 0
    private lateinit var selectedColorHex: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_category)

        val colorSpinner = findViewById<Spinner>(R.id.spn_color)
        val iconSpinner = findViewById<Spinner>(R.id.spn_icon)
        val btnCreate = findViewById<Button>(R.id.btn_create_category)
        val tieName = findViewById<TextInputEditText>(R.id.tie_name)

        val colorList = resources.getStringArray(R.array.color_name_array).toList()
        val iconList = resources.getStringArray(R.array.icon_name_array).toList()

        val adapter = ColorSpinnerAdapter(this, colorList)
        val adapterIcon = IconSpinnerAdapter(this, iconList)
        colorSpinner.adapter = adapter
        iconSpinner.adapter = adapterIcon

        colorSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parentView: AdapterView<*>, selectedItemView: View, position: Int, id: Long) {
                selectedColorName = parentView.getItemAtPosition(position).toString()
                selectedColorHex = adapter.getColorHex(selectedColorName)
//                Toast.makeText(this@NewCategoryActivity, "Selected Color: $selectedColorHex", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parentView: AdapterView<*>) {
                // Do nothing
            }
        }

        iconSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parentView: AdapterView<*>, selectedItemView: View, position: Int, id: Long) {
                val selectIconName = parentView.getItemAtPosition(position).toString()
                selectedIcon = adapterIcon.getDrawIcon(selectIconName)
//                Toast.makeText(this@NewCategoryActivity, "Selected Icon: $selectedIcon", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(p0: AdapterView<*>) {
                // do nothing
            }

        }

        btnCreate.setOnClickListener {
            categoryName = tieName.text.toString()
            val icon = selectedIcon
            val color = selectedColorHex
            if(categoryName.isNotEmpty()){
                val resultIntent = Intent()
                resultIntent.putExtra("CATEGORY_NAME", categoryName)
                resultIntent.putExtra("CATEGORY_COLOR", color)
                resultIntent.putExtra("CATEGORY_ICON", icon)
                setResult(Activity.RESULT_OK, resultIntent)
                finish()
            }

        }

    }
}