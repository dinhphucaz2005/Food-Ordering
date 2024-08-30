package com.example.foodordering.data.dto

import com.google.gson.annotations.SerializedName

data class CartDTO(
    @SerializedName("_id") val id: String,
    @SerializedName("products") val products: List<FoodDTO>,
    @SerializedName("id_user") val userId: String,
    @SerializedName("price") val total: Int,
)