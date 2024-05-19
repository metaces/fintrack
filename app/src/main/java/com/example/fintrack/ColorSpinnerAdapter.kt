package com.example.fintrack

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.core.content.ContextCompat
import java.util.Locale

class ColorSpinnerAdapter(context: Context, private val colors: List<String>): ArrayAdapter<String>(context, 0, colors ) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createView(position, convertView, parent)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createView(position, convertView, parent)
    }

    private fun createView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.custom_spinner_item, parent, false)

        val colorName = colors[position]

        val colorNameTextView = view.findViewById<TextView>(R.id.colorNameTextView)
        val colorView = view.findViewById<View>(R.id.colorView)

        colorNameTextView.text = colorName
        val color = getColor(colorName)

        val background = ContextCompat.getDrawable(context, R.drawable.circle_shape) as GradientDrawable
        background.setColor(color)
        colorView.background = background

        return view
    }

    private fun getColor(colorName: String): Int {
        return when (colorName.lowercase(Locale.getDefault())) {
            "black" -> 0xFF000000.toInt()
            "white" -> 0xFFFFFFFF.toInt()
            "red" -> 0xFFF54336.toInt()
            "pink" -> 0xFFEA1E61.toInt()
            "violet" ->0xFF9D28AC.toInt()
            "ocean_blue" -> 0xFF3F51B2.toInt()
            "blue" -> 0xFF1A96F0.toInt()
            "water_blue" -> 0xFF00BCD3.toInt()
            "water_green" -> 0xFF009689.toInt()
            "green" -> 0xFF4AAF57.toInt()
            "light_yellow" -> 0xFFFFEB4F.toInt()
            "medium_yellow" -> 0xFFFFC02D.toInt()
            "light_orange" -> 0xFFFF981F.toInt()
            "medium_orange" -> 0xFFFF5726.toInt()
            "brown" -> 0xFF7A5548.toInt()
            "grey" -> 0xFF607D8A.toInt()
            else -> 0xFF000000.toInt() // Default to black if unknown color
        }
    }
}