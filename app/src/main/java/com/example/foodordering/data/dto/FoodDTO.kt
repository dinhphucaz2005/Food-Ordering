package com.example.foodordering.data.dto


import com.google.gson.annotations.SerializedName

data class FoodDTO(
    val id: String,
    val name: String,
    val price: Int,
    val description: String,
    val imageUrl: List<String>
)