package com.example.models


import kotlinx.serialization.Serializable

@Serializable
data class Category(
    val name: String
)

@Serializable
data class FoodItem(
    val id: Int,
    val name: String,
    val description: String,
    val price: Double,
    val category: String
)