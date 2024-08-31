package com.example.foodordering.data.dto

import com.google.gson.annotations.SerializedName

data class InvoiceDTO(
    @SerializedName("_id") val id: String,
    @SerializedName("products") val foods: List<FoodDTO>,
    @SerializedName("id_user") val userId: String,
    @SerializedName("price") val total: Int,
    @SerializedName("status") val status: Boolean,
    @SerializedName("date_created") val dateCreated: String,
)
