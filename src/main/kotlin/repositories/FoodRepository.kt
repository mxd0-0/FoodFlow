package com.example.repositories

import com.example.models.Category
import com.example.models.FoodItem


object FoodRepository {
    private val categories = mutableListOf(
        Category("Déjeuner"),        // Lunch
        Category("Dîner"),           // Dinner
        Category("Petit Déjeuner"),  // Breakfast
        Category("Dessert"),         // Dessert
        Category("Boisson")          // Drink
    )


    private val foodItems = mutableListOf(
        // Déjeuner (Lunch)
        FoodItem(1, "Couscous", "Semoule cuite à la vapeur avec légumes, pois chiches et viande (agneau ou poulet)", 1500.0, "Déjeuner"),
        FoodItem(2, "Chakchouka", "Poêlée de légumes (tomates, poivrons, oignons) avec œufs", 900.0, "Déjeuner"),
        FoodItem(3, "Tajine Zitoun", "Plat mijoté à base de viande et d'olives vertes", 1200.0, "Déjeuner"),
        FoodItem(4, "Doubara", "Plat de pois chiches épicés originaire de Biskra", 800.0, "Déjeuner"),
        FoodItem(5, "Rechta", "Pâtes fines faites maison servies avec sauce blanche et poulet", 1300.0, "Déjeuner"),

        // Dîner
        FoodItem(6, "Loubia", "Haricots blancs en sauce rouge avec viande ou merguez", 1000.0, "Dîner"),
        FoodItem(7, "Mhadjeb", "Galette farcie aux légumes épicés", 700.0, "Dîner"),
        FoodItem(8, "Hmiss", "Salade de poivrons grillés et tomates servie avec pain maison", 600.0, "Dîner"),

        // Petit Déjeuner
        FoodItem(9, "Kesra", "Pain traditionnel algérien accompagné de beurre et miel", 500.0, "Petit Déjeuner"),
        FoodItem(10, "Baghrir", "Crêpes mille trous servies avec sirop de miel et beurre fondu", 600.0, "Petit Déjeuner"),

        // Dessert
        FoodItem(11, "Makrout", "Gâteau à base de semoule, farci aux dattes, frit et nappé de miel", 400.0, "Dessert"),
        FoodItem(12, "Zlabia", "Friandise frite trempée dans un sirop sucré, typique du Ramadan", 350.0, "Dessert"),
        FoodItem(13, "Kalb el louz", "Gâteau de semoule parfumé à l'eau de fleur d'oranger", 450.0, "Dessert"),

        // Boisson
        FoodItem(14, "Thé à la menthe", "Thé vert infusé avec de la menthe fraîche, typiquement servi sucré", 300.0, "Boisson"),
        FoodItem(15, "Lben", "Lait fermenté rafraîchissant, souvent servi avec les repas", 250.0, "Boisson"),
        FoodItem(16, "Jus d’orange pressé", "Jus d’orange naturel fraîchement pressé", 500.0, "Boisson")
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