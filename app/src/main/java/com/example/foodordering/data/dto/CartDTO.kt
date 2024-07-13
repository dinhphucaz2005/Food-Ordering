package com.example.foodordering.data.dto


import com.google.gson.annotations.SerializedName

data class CartDTO(
    @SerializedName("date_created") val dateCreated: String,
    @SerializedName("_id") val id: String,
    @SerializedName("id_user") val idUser: String,
    @SerializedName("price") val price: Int,
    @SerializedName("products") val productsDTO: List<ProductDTO>,
    @SerializedName("__v") val v: Int
)