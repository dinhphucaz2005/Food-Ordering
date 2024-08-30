package com.example.foodordering.domain.repository

import com.example.foodordering.data.dto.CartDTO
import com.example.foodordering.data.dto.FoodDTO
import com.example.foodordering.domain.model.Food
import com.example.foodordering.util.AppResource

interface CustomerRepository {

    suspend fun getFoods(): AppResource<List<FoodDTO>>

    suspend fun addCart(productId: String): AppResource<CartDTO>

    suspend fun getFoodById(id: String): FoodDTO

    suspend fun getCart(): AppResource<CartDTO>

    suspend fun updateCart(foodId: String, cartId: String, quantity: Int): AppResource<*>
}