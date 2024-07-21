package com.example.foodordering.data.repository

import android.net.Uri
import com.example.foodordering.di.AppModule
import com.example.foodordering.domain.model.Bill
import com.example.foodordering.domain.model.Food
import com.example.foodordering.domain.repository.ManagerRepository
import com.example.foodordering.util.AppResource
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import java.util.concurrent.CountDownLatch
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class ManagerRepositoryImpl : ManagerRepository {
    override suspend fun getFoods(): AppResource<List<Food>> {
        TODO("Not yet implemented")
    }

    override suspend fun getBills(): AppResource<List<Bill>> {
        TODO("Not yet implemented")
    }

    override suspend fun addFood(food: Food, imageList: List<Uri?>): AppResource<Food> {
        TODO("Not yet implemented")
    }

    override suspend fun uploadImagesSuspend(imageList: List<Uri?>, name: String): List<String> {
        TODO("Not yet implemented")
    }
}