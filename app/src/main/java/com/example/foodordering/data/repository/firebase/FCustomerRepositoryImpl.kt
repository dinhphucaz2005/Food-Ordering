package com.example.foodordering.data.repository.firebase

import com.example.foodordering.data.dto.CartDTO
import com.example.foodordering.domain.model.Food
import com.example.foodordering.domain.repository.CustomerRepository
import com.example.foodordering.util.AppResource
import com.example.foodordering.util.FirebaseChild
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.StorageReference
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class FCustomerRepositoryImpl(
    private val database: DatabaseReference,
) : CustomerRepository {
    override suspend fun getFoods(): AppResource<List<Food>> {
        return suspendCoroutine { continuation ->
            val dataRef = database.child(FirebaseChild.FOOD)
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

    override suspend fun addCart(idProduct: String): AppResource<CartDTO> {
        TODO()
    }
}