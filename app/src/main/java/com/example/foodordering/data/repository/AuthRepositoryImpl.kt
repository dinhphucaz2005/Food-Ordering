package com.example.foodordering.data.repository

import com.example.foodordering.data.dto.ResponseDTO
import com.example.foodordering.data.dto.UserDTO
import com.example.foodordering.data.remote.ApiService
import com.example.foodordering.domain.repository.AuthRepository
import com.example.foodordering.provider.UserProvider
import com.example.foodordering.util.AppResource
import com.example.foodordering.util.ValidationHelper
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class AuthRepositoryImpl(
    private val apiService: ApiService,
    private val userProvider: UserProvider
) : AuthRepository {
    override suspend fun login(email: String, password: String): AppResource<UserDTO> {
        if (ValidationHelper.isInvalidEmail(email))
            return AppResource.Error("Invalid email")
        if (ValidationHelper.isInvalidPassword(password))
            return AppResource.Error("Invalid password")
        return suspendCoroutine { continuation ->
            val body = HashMap<String, Any>()
            body["email"] = email
            body["password"] = password
            apiService.signIn(body).enqueue(object : Callback<ResponseDTO<UserDTO>> {
                override fun onResponse(
                    call: Call<ResponseDTO<UserDTO>>,
                    response: Response<ResponseDTO<UserDTO>>
                ) {
                    if (response.isSuccessful) {
                        val post = response.body()
                        if (post != null) {
                            post.data?.let { userProvider.saveUser(email, password, it.token) }
                            continuation.resume(AppResource.Success(post.data))
                        } else {
                            continuation.resume(AppResource.Error("User not found"))
                        }
                    } else {
                        val json = JSONObject(response.errorBody()?.string() ?: "{}")
                        val errorMessage = json.optString("message")
                        continuation.resume(AppResource.Error("Login failed: $errorMessage"))
                    }

                }

                override fun onFailure(call: Call<ResponseDTO<UserDTO>>, throwable: Throwable) {
                    continuation.resume(AppResource.Error("API call failed: ${throwable.message}"))
                }

            })
        }
    }

    override suspend fun register(
        email: String,
        name: String,
        password: String,
        phoneNumber: String
    ): AppResource<UserDTO> {
        if (ValidationHelper.isInvalidEmail(email))
            return AppResource.Error("Invalid email")
        if (ValidationHelper.isInvalidPassword(password))
            return AppResource.Error("Invalid password")
        if (ValidationHelper.isInvalidPhoneNumber(phoneNumber))
            return AppResource.Error("Invalid phone number")
        return suspendCoroutine { continuation ->
            val body = HashMap<String, Any>()
            body["name"] = name
            body["email"] = email
            body["password"] = password
            body["phone"] = phoneNumber
            body["address"] = "Ho Chi Minh City"
            apiService.signUp(body).enqueue(object : Callback<ResponseDTO<UserDTO>> {
                override fun onResponse(
                    p0: Call<ResponseDTO<UserDTO>>,
                    p1: Response<ResponseDTO<UserDTO>>
                ) {
                    if (p1.isSuccessful) {
                        val post = p1.body()
                        if (post != null) {
                            continuation.resume(AppResource.Success(post.data))
                        } else {
                            continuation.resume(AppResource.Error("User not found"))
                        }
                    } else {
                        val json = JSONObject(p1.errorBody()?.string() ?: "{}")
                        val errorMessage = json.optString("message")
                        continuation.resume(AppResource.Error("Signup failed: $errorMessage"))
                    }
                }

                override fun onFailure(p0: Call<ResponseDTO<UserDTO>>, p1: Throwable) {
                    continuation.resume(AppResource.Error("API call failed: ${p1.message}"))
                }
            })
        }
    }
}