package com.example.foodordering.domain.model


data class CartItem(
    var food: Food = Food(),
    var quantity: Int = 0,
)