package com.example.foodordering.data.repository

import com.example.foodordering.data.dto.ResponseDTO
import com.example.foodordering.data.dto.UserDTO
import com.example.foodordering.data.remote.ApiService
import com.example.foodordering.domain.repository.AuthRepository
import com.example.foodordering.util.AppResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class AuthRepositoryImpl(
    private val apiService: ApiService
) : AuthRepository {
    override suspend fun login(email: String, password: String): AppResource<UserDTO> {
        TODO("Not yet implemented")
    }

    override suspend fun register(
        username: String,
        email: String,
        password: String,
        phoneNumber: String
    ): AppResource<UserDTO> {
        TODO("Not yet implemented")
    }


}