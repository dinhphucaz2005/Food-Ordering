package com.example.foodordering.data.repository

import android.net.Uri
import com.example.foodordering.domain.model.Bill
import com.example.foodordering.domain.model.Food
import com.example.foodordering.domain.repository.ManagerRepository
import com.example.foodordering.util.AppResource

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

    override suspend fun removeFood(foodId: String): AppResource<String> {
        TODO("Not yet implemented")
    }

    override suspend fun uploadImagesSuspend(imageList: List<Uri?>, name: String): List<String> {
        TODO("Not yet implemented")
    }
}