package com.example.foodordering.domain.model

import com.example.foodordering.data.dto.CartDTO


data class Cart(
    val id: String,
    val foods: List<Food>,
    var totalPrice: Int
) {
    fun addFood(foodId: String) {
        val food = foods.find { it.id == foodId }
        if (food != null) {
            totalPrice += food.price
            food.quantity++
        }
    }

    fun removeFood(foodId: String) {
        val food = foods.find { it.id == foodId }
        if (food != null && food.quantity > 0) {
            totalPrice -= food.price
            food.quantity--
        }
    }

    constructor(cartDTO: CartDTO) : this(
        id = cartDTO.id,
        foods = cartDTO.products.map { Food(it) },
        totalPrice = cartDTO.total
    )
}