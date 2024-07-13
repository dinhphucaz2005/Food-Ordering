package com.example.foodordering.data.repository

import com.example.foodordering.di.AppModule
import com.example.foodordering.domain.model.Food
import com.example.foodordering.domain.repository.ManagerRepository
import com.example.foodordering.util.AppResource
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import kotlin.coroutines.resume

class ManagerRepositoryImpl : ManagerRepository {

    private val database = AppModule.provideDatabase()

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
}