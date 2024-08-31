package com.example.foodordering.data.repository

import android.util.Log
import com.example.foodordering.data.dto.CartDTO
import com.example.foodordering.data.dto.FoodDTO
import com.example.foodordering.data.dto.InvoiceDTO
import com.example.foodordering.data.dto.ResponseDTO
import com.example.foodordering.data.remote.ApiService
import com.example.foodordering.data.remote.ApiServiceWithToken
import com.example.foodordering.domain.repository.CustomerRepository
import com.example.foodordering.util.AppResource
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class CustomerRepositoryImpl(
    private val apiService: ApiService,
    private val apiServiceWithToken: ApiServiceWithToken
) : CustomerRepository {

    var foods = listOf<FoodDTO>()

    override suspend fun getFoods(): AppResource<List<FoodDTO>> {
        return suspendCoroutine { continuation ->
            apiService.getFoods().enqueue(object : Callback<ResponseDTO<List<FoodDTO>>> {
                override fun onResponse(
                    p0: Call<ResponseDTO<List<FoodDTO>>>,
                    p1: Response<ResponseDTO<List<FoodDTO>>>
                ) {
                    if (p1.isSuccessful) {
                        val post = p1.body()
                        if (post != null) {
                            post.data?.let { foods = it }
                            continuation.resume(AppResource.Success(post.data))
                        } else {
                            continuation.resume(AppResource.Error("Foods not found"))
                        }
                    } else {
                        val json = JSONObject(p1.errorBody()?.string() ?: "{}")
                        val errorMessage = json.optString("message")
                        continuation.resume(AppResource.Error(errorMessage))
                    }
                }

                override fun onFailure(p0: Call<ResponseDTO<List<FoodDTO>>>, p1: Throwable) {
                    continuation.resume(AppResource.Error(p1.message ?: "Unknown error"))
                }

            })
        }
    }

    override suspend fun addCart(productId: String): AppResource<CartDTO> {
        return suspendCoroutine { continuation ->
            val body = HashMap<String, Any>()
            body["id_product"] = productId
            apiServiceWithToken.addCart(body).enqueue(object : Callback<ResponseDTO<CartDTO>> {
                override fun onResponse(
                    p0: Call<ResponseDTO<CartDTO>>,
                    p1: Response<ResponseDTO<CartDTO>>
                ) {
                    if (p1.isSuccessful) {
                        val post = p1.body()
                        if (post != null) {
                            continuation.resume(AppResource.Success(post.data))
                        } else {
                            continuation.resume(AppResource.Error("Cart not found"))
                        }
                    } else {
                        val json = JSONObject(p1.errorBody()?.string() ?: "{}")
                        val errorMessage = json.optString("message")
                        continuation.resume(AppResource.Error(errorMessage))
                    }
                }

                override fun onFailure(p0: Call<ResponseDTO<CartDTO>>, p1: Throwable) {
                    continuation.resume(AppResource.Error(p1.message ?: "Unknown error"))
                }
            })
        }
    }

    override suspend fun getFood(foodId: String): FoodDTO {
        return foods.find { it.id == foodId } ?: throw Exception("Food not found")
    }

    override suspend fun getCart(): AppResource<CartDTO> {
        return suspendCoroutine { continuation ->
            apiServiceWithToken.getCart().enqueue(object : Callback<ResponseDTO<CartDTO>> {
                override fun onResponse(
                    p0: Call<ResponseDTO<CartDTO>>,
                    p1: Response<ResponseDTO<CartDTO>>
                ) {
                    if (p1.isSuccessful) {
                        val post = p1.body()
                        if (post != null) {
                            continuation.resume(AppResource.Success(post.data))
                        } else {
                            continuation.resume(AppResource.Error("Cart not found"))
                        }
                    } else {
                        val json = JSONObject(p1.errorBody()?.string() ?: "{}")
                        val errorMessage = json.optString("message")
                        continuation.resume(AppResource.Error(errorMessage))
                    }
                }

                override fun onFailure(p0: Call<ResponseDTO<CartDTO>>, p1: Throwable) {
                    continuation.resume(AppResource.Error(p1.message ?: "Unknown error"))
                }
            })
        }
    }

    companion object {
        const val TAG = "CustomerRepositoryImpl"
    }

    override suspend fun updateCart(foodId: String, cartId: String, quantity: Int): AppResource<*> {
        return suspendCoroutine { continuation ->
            val body = HashMap<String, Any>()
            body["id_product"] = foodId
            body["id_cart"] = cartId
            body["quantity"] = quantity
            apiServiceWithToken.updateCart(body)
                .enqueue(object : Callback<ResponseDTO<CartDTO>> {
                    override fun onResponse(
                        p0: Call<ResponseDTO<CartDTO>>,
                        p1: Response<ResponseDTO<CartDTO>>
                    ) {
                        if (p1.isSuccessful) {
                            val post = p1.body()
                            Log.d(TAG, "onResponse: ${post?.data}")
                            if (post != null) {
                                continuation.resume(AppResource.Success(null))
                            } else {
                                continuation.resume(AppResource.Error("Cart not found"))
                            }
                        } else {
                            Log.d(TAG, "onResponse: ${p1.errorBody()?.string()}")
                            try {
                                val json = JSONObject(p1.errorBody()?.string() ?: "{}")
                                val errorMessage = json.optString("message")
                                continuation.resume(AppResource.Error(errorMessage))
                            } catch (e: JSONException) {
                                continuation.resume(AppResource.Error(e.message ?: "Unknown error"))
                            }
                        }
                    }

                    override fun onFailure(p0: Call<ResponseDTO<CartDTO>>, p1: Throwable) {
                        continuation.resume(AppResource.Error(p1.message ?: "Unknown error"))
                    }
                })
        }
    }

    override suspend fun confirmCart(cartId: String): AppResource<*> {
        return suspendCoroutine { continuation ->
            val body = HashMap<String, Any>()
            body["id_cart"] = cartId
            body["status"] = true
            apiServiceWithToken.confirmCart(body).enqueue(object : Callback<ResponseDTO<String>> {
                override fun onResponse(
                    p0: Call<ResponseDTO<String>>,
                    p1: Response<ResponseDTO<String>>
                ) {
                    if (p1.isSuccessful) {
                        val post = p1.body()
                        if (post != null) {
                            continuation.resume(AppResource.Success(post))
                        } else {
                            continuation.resume(AppResource.Error("Cart not found"))
                        }
                    } else {
                        val json = JSONObject(p1.errorBody()?.string() ?: "{}")
                        val errorMessage = json.optString("message")
                        continuation.resume(AppResource.Error(errorMessage))
                    }
                }

                override fun onFailure(p0: Call<ResponseDTO<String>>, p1: Throwable) {
                    continuation.resume(AppResource.Error(p1.message ?: "Unknown error"))
                }
            })
        }
    }

    private var invoiceDTOs: List<InvoiceDTO> = emptyList()

    override suspend fun getInvoices(): AppResource<List<InvoiceDTO>> {
        return suspendCoroutine { continuation ->
            apiServiceWithToken.getInvoices()
                .enqueue(object : Callback<ResponseDTO<List<InvoiceDTO>>> {
                    override fun onResponse(
                        p0: Call<ResponseDTO<List<InvoiceDTO>>>,
                        p1: Response<ResponseDTO<List<InvoiceDTO>>>
                    ) {
                        if (p1.isSuccessful) {
                            val post = p1.body()
                            if (post != null) {
                                invoiceDTOs = post.data ?: emptyList()
                                continuation.resume(AppResource.Success(post.data))
                            } else {
                                continuation.resume(AppResource.Error("Invoices not found"))
                            }
                        } else {
                            val json = JSONObject(p1.errorBody()?.string() ?: "{}")
                            val errorMessage = json.optString("message")
                            continuation.resume(AppResource.Error(errorMessage))
                        }
                    }

                    override fun onFailure(
                        p0: Call<ResponseDTO<List<InvoiceDTO>>>,
                        p1: Throwable
                    ) {
                        continuation.resume(AppResource.Error(p1.message ?: "Unknown error"))
                    }
                })
        }
    }

    override suspend fun getInvoice(id: String): InvoiceDTO? {
        return invoiceDTOs.find { it.id == id }
    }
}