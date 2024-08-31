package com.example.foodordering.data.remote

import com.example.foodordering.data.dto.CartDTO
import com.example.foodordering.data.dto.InvoiceDTO
import com.example.foodordering.data.dto.ResponseDTO
import com.example.foodordering.domain.model.Invoice
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiServiceWithToken {

    @POST("/cart/add")
    fun addCart(@Body body: HashMap<String, Any>): Call<ResponseDTO<CartDTO>>

    @GET("/cart")
    fun getCart(): Call<ResponseDTO<CartDTO>>

    @POST("/cart/update")
    fun updateCart(@Body body: HashMap<String, Any>): Call<ResponseDTO<CartDTO>>

    @POST("/cart/conform")
    fun confirmCart(@Body body: HashMap<String, Any>): Call<ResponseDTO<String>>

    @POST("/order/history")
    fun getInvoices(): Call<ResponseDTO<List<InvoiceDTO>>>
}