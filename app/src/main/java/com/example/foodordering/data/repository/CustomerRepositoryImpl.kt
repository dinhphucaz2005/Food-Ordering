package com.example.foodordering.data.repository

import android.util.Log
import com.example.foodordering.data.dto.CartDTO
import com.example.foodordering.data.dto.ResponseDTO
import com.example.foodordering.data.remote.ApiService
import com.example.foodordering.domain.repository.CustomerRepository
import com.example.foodordering.util.AppResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class CustomerRepositoryImpl(
    private val apiService: ApiService
) : CustomerRepository {

    override suspend fun addCart(idProduct: String): AppResource<CartDTO> {
        return suspendCoroutine { continuation ->
            val body = HashMap<String, Any>()
            body["id_product"] = idProduct
            apiService.addCart(body).enqueue(object : Callback<ResponseDTO<CartDTO>> {
                override fun onResponse(
                    call: Call<ResponseDTO<CartDTO>>,
                    response: Response<ResponseDTO<CartDTO>>
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

                override fun onFailure(call: Call<ResponseDTO<CartDTO>>, throwable: Throwable) {
                    val message = throwable.message
                    message?.let { AppResource.Error(it) }?.let { continuation.resume(it) }
                }

            })
        }
    }

}