package com.example.foodordering.domain.repository

import com.example.foodordering.data.dto.CartDTO
import com.example.foodordering.domain.model.Food
import com.example.foodordering.util.AppResource

interface CustomerRepository {

    suspend fun getFoods() : AppResource<List<Food>>

    suspend fun addCart(idProduct: String): AppResource<CartDTO>

}