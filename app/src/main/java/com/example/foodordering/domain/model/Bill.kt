package com.example.foodordering.domain.model

import kotlin.random.Random

data class CartItemDTO(
    val foodId: String,
    val quantity: Int
) {
    constructor() : this(
        foodId = "",
        quantity = Random.nextInt(1, 5)
    )
}

data class Bill(
    val id: String,
    val userId: String,
    val time: Long,
    val total: Int,
    val cart: List<CartItemDTO>
) {
    constructor() : this(
        id = "",
        userId = "",
        time = System.currentTimeMillis(),
        total = (Random.nextInt(1, 5) * 1000),
        cart = emptyList()
    )
}