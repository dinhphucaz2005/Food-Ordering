package com.example.foodordering.domain.repository

import android.net.Uri
import com.example.foodordering.domain.model.Bill
import com.example.foodordering.domain.model.Food
import com.example.foodordering.util.AppResource

interface ManagerRepository {

    suspend fun getFoods(): AppResource<List<Food>>

    suspend fun getBills(): AppResource<List<Bill>>

    suspend fun addFood(food: Food, imageList: List<Uri?>): AppResource<Food>

    suspend fun uploadImagesSuspend(imageList: List<Uri?>, name: String): List<String>
}