package com.example.repositories

import com.example.models.Category
import com.example.models.FoodItem


object FoodRepository {
    private val categories = mutableListOf(
        Category("Dinner"),
        Category("Lunch"),
        Category("Dessert"),
        Category("Drink")
    )

    private val foodItems = mutableListOf(
        FoodItem(1, "Rovioli", "Piza Impermeable Bnina Bela3des wel lobya", 2000.0, "Dinner"),
        FoodItem(2, "Pizza", "Impermeable Bnina Bela3des wel lobya", 2000.0, "Lunch")
    )

    fun getAllCategories(): List<Category> = categories.toList()

    fun getAllFoodItems(category: String? = null): List<FoodItem> {
        return if (category != null) {
            foodItems.filter { it.category.equals(category, ignoreCase = true) }
        } else {
            foodItems.toList()
        }
    }

    fun getFoodItem(id: Int): FoodItem? = foodItems.find { it.id == id }

    fun addFoodItem(item: FoodItem): FoodItem {
        val newId = foodItems.maxOfOrNull { it.id }?.plus(1) ?: 1
        val newItem = item.copy(id = newId)
        foodItems.add(newItem)
        return newItem
    }

    fun updateFoodItem(id: Int, item: FoodItem): Boolean {
        val index = foodItems.indexOfFirst { it.id == id }
        return if (index != -1) {
            foodItems[index] = item.copy(id = id)
            true
        } else {
            false
        }
    }

    fun deleteFoodItem(id: Int): Boolean = foodItems.removeIf { it.id == id }
}