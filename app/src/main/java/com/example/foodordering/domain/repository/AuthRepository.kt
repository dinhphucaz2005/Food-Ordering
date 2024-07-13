package com.example.foodordering.domain.repository

import com.example.foodordering.data.dto.UserDTO
import com.example.foodordering.util.AppResource

interface AuthRepository {

    suspend fun login(email: String, password: String): AppResource<UserDTO>

    suspend fun register(
        name: String,
        email: String,
        password: String,
        phoneNumber: String
    ): AppResource<UserDTO>

}