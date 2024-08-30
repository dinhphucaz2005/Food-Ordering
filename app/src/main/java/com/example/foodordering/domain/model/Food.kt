package com.example.foodordering.domain.model

import com.example.foodordering.data.dto.FoodDTO
import com.example.foodordering.util.AppConstants
import java.util.UUID
import kotlin.random.Random

data class Food(
    var id: String,
    var name: String,
    var price: Int,
    var gallery: List<String>,
    var quantity: Int,
) {
    constructor(foodDTO: FoodDTO) : this(
        foodDTO.id,
        foodDTO.name,
        foodDTO.price,
        mutableListOf<String>().apply {
            this.add(AppConstants.BASE_URL + "/" + foodDTO.img)
            foodDTO.gallery.forEach { this.add(AppConstants.BASE_URL + "/" + it) }
        },
        foodDTO.quantity
    )
}