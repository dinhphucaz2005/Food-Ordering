package com.example.foodordering.data.dto


import com.google.gson.annotations.SerializedName

data class ProductDTO(
    @SerializedName("address") val address: String,
    @SerializedName("date_created") val dateCreated: String,
    @SerializedName("date_updated") val dateUpdated: Any,
    @SerializedName("gallery") val gallery: List<String>,
    @SerializedName("_id") val id: String,
    @SerializedName("img") val img: String,
    @SerializedName("name") val name: String,
    @SerializedName("price") val price: Int,
    @SerializedName("quantity") val quantity: Int,
    @SerializedName("__v") val v: Int
)