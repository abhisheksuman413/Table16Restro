package com.fps69.myapplication

data class DummeyUserData(
    val limit: Int,
    val recipes: List<RecipeDummyUserData>,
    val skip: Int,
    val total: Int
)