package com.example.fintrack

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import java.util.Locale

class IconSpinnerAdapter(context: Context, private val icons: List<String>): ArrayAdapter<String>(context, 0, icons) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createView(position, convertView, parent)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createView(position, convertView, parent)
    }

    private fun createView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.custom_spinner_item_icon, parent, false)

        val iconName = icons[position]

        val iconNameTextView = view.findViewById<TextView>(R.id.tv_icon_name)
        val iconView = view.findViewById<ImageView>(R.id.imageView_icon)

        iconNameTextView.text = iconName
        val icon = getDrawIcon(iconName)

        iconView.setImageResource(icon)

        return view
    }

    fun getDrawIcon(iconName: String): Int {
        return when (iconName.lowercase(Locale.getDefault())) {
            "car" -> R.drawable.ic_car
            "clothes" -> R.drawable.ic_clothes
            "credit_card" -> R.drawable.ic_credit_card
            "electricity" -> R.drawable.ic_electricity
            "game_control" -> R.drawable.ic_game_control
            "gas_station" -> R.drawable.ic_gas_station
            "graphic" -> R.drawable.ic_graphic
            "home" -> R.drawable.ic_home
            "key" -> R.drawable.ic_key
            "shopping_cart" -> R.drawable.ic_shopping_cart
            "water_drop" -> R.drawable.ic_water_drop
            "wifi" -> R.drawable.ic_wifi
            else -> {
                R.drawable.ic_wifi
            }
        }
    }
}