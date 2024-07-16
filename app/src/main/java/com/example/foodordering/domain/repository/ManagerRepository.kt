package com.example.foodordering.domain.repository

import com.example.foodordering.domain.model.Bill
import com.example.foodordering.domain.model.Food
import com.example.foodordering.util.AppResource

interface ManagerRepository {

    suspend fun getFoods(): AppResource<List<Food>>

    suspend fun getBills(): AppResource<List<Bill>>

}