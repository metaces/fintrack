package com.example.fintrack

import androidx.annotation.DrawableRes

data class Category(
    val name: String,
    val color: String,
    val total: Float,
    @DrawableRes val icon: Int
)
