package com.example.fintrack

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class ExpenseListAdapter: ListAdapter<Expense, ExpenseListAdapter.ExpenseViewHolder>(ExpenseDiffUtils()) {


    // criar a viewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpenseViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_expense_category, parent, false)

        return ExpenseViewHolder(view)
    }

    // ligar os dados a view
    override fun onBindViewHolder(holder: ExpenseViewHolder, position: Int) {
        val expense = getItem(position)
        holder.bind(expense)
    }

    // segurar os dados (data class Expense)
    class ExpenseViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val tvNameExpense = view.findViewById<TextView>(R.id.tv_name_expense)
        private val tvValueExpense = view.findViewById<TextView>(R.id.tv_value_expense)

        fun bind(expenseCategory: Expense) {
            tvNameExpense.text = expenseCategory.name.toString()
            tvValueExpense.text = "-R$"+expenseCategory.value.toString()
        }
    }

    //recupera quando houver alteracao na lista
    class ExpenseDiffUtils: DiffUtil.ItemCallback<Expense>() {
        override fun areItemsTheSame(oldItem: Expense, newItem: Expense): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Expense, newItem: Expense): Boolean {
            return oldItem.name == newItem.name
        }

    }

}