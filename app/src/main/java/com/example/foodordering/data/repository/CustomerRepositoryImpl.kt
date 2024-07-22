package com.example.foodordering.data.repository

import com.example.foodordering.data.dto.CartDTO
import com.example.foodordering.data.remote.ApiService
import com.example.foodordering.domain.model.Food
import com.example.foodordering.domain.repository.CustomerRepository
import com.example.foodordering.util.AppResource

class CustomerRepositoryImpl(
    private val apiService: ApiService
) : CustomerRepository {
    override suspend fun getFoods(): AppResource<List<Food>> {
        TODO("Not yet implemented")
    }

    override suspend fun addCart(idProduct: String): AppResource<CartDTO> {
        TODO("Not yet implemented")
    }
}