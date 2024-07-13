package com.example.foodordering.domain.repository

import com.example.foodordering.data.dto.CartDTO
import com.example.foodordering.util.AppResource

interface CustomerRepository {

    suspend fun addCart(idProduct: String): AppResource<CartDTO>

}