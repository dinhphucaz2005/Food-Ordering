package com.example.foodordering.data.repository

import com.example.foodordering.data.dto.CartDTO
import com.example.foodordering.data.dto.ResponseDTO
import com.example.foodordering.data.remote.ApiService
import com.example.foodordering.di.AppModule
import com.example.foodordering.domain.model.Food
import com.example.foodordering.domain.repository.CustomerRepository
import com.example.foodordering.util.AppResource
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class CustomerRepositoryImpl(
    private val apiService: ApiService
) : CustomerRepository {

    private val database by lazy {
        AppModule.provideDatabase()
    }

    override suspend fun getFoods(): AppResource<List<Food>> {
        return withContext(Dispatchers.IO) {
            suspendCancellableCoroutine { continuation ->
                val dataRef = database.child("food")
                dataRef.addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        val foodList = mutableListOf<Food>()
                        for (foodSnapshot in dataSnapshot.children) {
                            val food = foodSnapshot.getValue(Food::class.java)
                            food?.let { foodList.add(it) }
                        }
                        continuation.resume(AppResource.Success(foodList))
                    }

                    override fun onCancelled(databaseError: DatabaseError) {
                        continuation.resume(AppResource.Error(databaseError.message))
                    }
                })
            }
        }
    }

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