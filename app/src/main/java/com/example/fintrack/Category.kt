package com.example.fintrack

import androidx.annotation.DrawableRes

data class Category(
    val id: Int,
    val name: String,
    val color: String,
    val total: Float,
    @DrawableRes val icon: Int
)
