package com.example.foodordering.data.dto

import com.google.gson.annotations.SerializedName

data class UserDTO(
    @SerializedName("email") val email: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("phone") val phone: String?,
    @SerializedName("token") val token: String?
)