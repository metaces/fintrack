package com.example.fintrack

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class NewCategoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_category)

        val colorSpinner = findViewById<Spinner>(R.id.spn_color)
        val iconSpinner = findViewById<Spinner>(R.id.spn_icon)

        val colorList = resources.getStringArray(R.array.color_name_array).toList()
        val iconList = resources.getStringArray(R.array.icon_name_array).toList()

        val adapter = ColorSpinnerAdapter(this, colorList)
        val adapterIcon = IconSpinnerAdapter(this, iconList)
        colorSpinner.adapter = adapter
        iconSpinner.adapter = adapterIcon

        colorSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parentView: AdapterView<*>, selectedItemView: View, position: Int, id: Long) {
                val selectedColorName = parentView.getItemAtPosition(position).toString()
                val selectedColorHex = adapter.getColorHex(selectedColorName)
                Toast.makeText(this@NewCategoryActivity, "Selected Color: $selectedColorHex", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parentView: AdapterView<*>) {
                // Do nothing
            }
        }

        iconSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parentView: AdapterView<*>, selectedItemView: View, position: Int, id: Long) {
                val selectIconName = parentView.getItemAtPosition(position).toString()
                val selectIcon = adapterIcon.getDrawIcon(selectIconName)
                Toast.makeText(this@NewCategoryActivity, "Selected Icon: $selectIcon", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(p0: AdapterView<*>) {
                // do nothing
            }

        }

    }
}