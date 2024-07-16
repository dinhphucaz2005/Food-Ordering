package com.example.foodordering.domain.model

import kotlin.random.Random
import kotlin.random.nextInt

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
    val time: String,
    val total: Long,
    val cart: List<CartItemDTO>
) {
    constructor() : this(
        time = "System.currentTimeMillis()",
        total = (Random.nextInt(1, 5) * 1000).toLong(),
        cart = emptyList()
    )
}