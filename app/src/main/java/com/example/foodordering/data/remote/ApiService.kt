package com.example.foodordering.data.remote

import com.example.foodordering.data.dto.CartDTO
import com.example.foodordering.data.dto.ResponseDTO
import com.example.foodordering.data.dto.UserDTO
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("user/sign-in")
    fun signIn(@Body body: HashMap<String, Any>): Call<ResponseDTO<UserDTO>>

    @POST("user/sign-up")
    fun signUp(@Body body: HashMap<String, Any>): Call<ResponseDTO<UserDTO>>

    @POST("cart/add")
    fun addCart(@Body body: HashMap<String, Any>): Call<ResponseDTO<CartDTO>>
}