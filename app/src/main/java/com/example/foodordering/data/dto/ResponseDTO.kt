package com.example.foodordering.data.dto

import com.google.gson.annotations.SerializedName

data class ResponseDTO<T>(
    @SerializedName("result") val result: Int,
    @SerializedName("data") val data: T? = null,
    @SerializedName("message") val message: String
)