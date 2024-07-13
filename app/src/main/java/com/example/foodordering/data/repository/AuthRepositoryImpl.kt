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
        return suspendCoroutine { continuation ->
            val body = HashMap<String, Any>()
            body["email"] = email
            body["password"] = password

            apiService.signIn(body).enqueue(object :
                Callback<ResponseDTO<UserDTO>> {
                override fun onResponse(
                    call: Call<ResponseDTO<UserDTO>>,
                    response: Response<ResponseDTO<UserDTO>>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if (responseBody != null) {
                            if (responseBody.result == 1) {
                                continuation.resume(AppResource.Success(responseBody.data))
                            } else {
                                continuation.resume(AppResource.Error(responseBody.message))
                            }
                        } else {
                            continuation.resume(AppResource.Error("Response body is null"))
                        }
                    } else {
                        continuation.resume(AppResource.Error("Request failed"))
                    }
                }

                override fun onFailure(call: Call<ResponseDTO<UserDTO>>, t: Throwable) {
                    val message = t.message
                    message?.let { AppResource.Error(it) }?.let { continuation.resume(it) }
                }
            }
            )
        }
    }

    override suspend fun register(
        name: String,
        email: String,
        password: String,
        phoneNumber: String
    ): AppResource<UserDTO> {
        return suspendCoroutine { continuation ->
            val body = HashMap<String, Any>()
            body["name"] = name
            body["email"] = email
            body["password"] = password

            apiService.signUp(body).enqueue(object :
                Callback<ResponseDTO<UserDTO>> {
                override fun onResponse(
                    call: Call<ResponseDTO<UserDTO>>,
                    response: Response<ResponseDTO<UserDTO>>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()

                        if (responseBody != null) {
                            if (responseBody.result == 1) {
                                continuation.resume(AppResource.Success(responseBody.data!!))
                            } else {
                                continuation.resume(AppResource.Error(responseBody.message))
                            }
                        } else {
                            continuation.resume(AppResource.Error("Response body is null"))
                        }
                    }
                }

                override fun onFailure(call: Call<ResponseDTO<UserDTO>>, throwable: Throwable) {
                    continuation.resume(AppResource.Error("Request failed"))
                }
            })
        }
    }
}