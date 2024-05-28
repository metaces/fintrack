package com.example.fintrack

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class CategoryListAdapter: ListAdapter<Category, CategoryListAdapter.CategoryViewHolder>(CategoryDiffUtils()) {

    private lateinit var onClickListener: (Category) -> Unit


    //criar a viewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_category, parent, false)

        return CategoryViewHolder(view)
    }

    //ligar os dados a view
    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = getItem(position)
        holder.bind(category, onClickListener)
    }

    fun onClickListener(onClick: (Category) -> Unit) {
        onClickListener = onClick
    }


    //segurar os dados ( data class Category )
    class CategoryViewHolder(private val view: View): RecyclerView.ViewHolder(view) {
        private val ivIcon = view.findViewById<ImageView>(R.id.imageView_icon)
        private val ivColor = view.findViewById<ImageView>(R.id.imageView_color)
        private val tvCategoryName = view.findViewById<TextView>(R.id.tv_category_name)
        private val tvCategoryTotalExpense = view.findViewById<TextView>(R.id.tv_category_total_expense)

        fun bind(category: Category, onClick: (Category) -> Unit) {
            ivIcon.setImageResource(category.icon)
            ivColor.setColorFilter(Color.parseColor(category.color))

            tvCategoryName.text = category.name
            tvCategoryTotalExpense.text = "-R$"+category.total.toString()

            view.setOnClickListener {
                onClick.invoke(category)
            }
        }
    }

    // compera quando houve alteracao na lista
    class CategoryDiffUtils: DiffUtil.ItemCallback<Category>() {
        override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem.total == newItem.total
        }

    }

}