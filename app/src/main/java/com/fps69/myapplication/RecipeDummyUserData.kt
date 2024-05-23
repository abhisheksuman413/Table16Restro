package com.fps69.myapplication

data class RecipeDummyUserData(
    val caloriesPerServing: Int,
    val cookTimeMinutes: Int,
    val cuisine: String,
    val difficulty: String,
    val id: Int,
    var image: String,
    val ingredients: List<String>,
    val instructions: List<String>,
    val mealType: List<String>,
    var name: String,
    var prepTimeMinutes: Int,
    val rating: Double,
    val reviewCount: Int,
    val servings: Int,
    val tags: List<String>,
    val userId: Int
)