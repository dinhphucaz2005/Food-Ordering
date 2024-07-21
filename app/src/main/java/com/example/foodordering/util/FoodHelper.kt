package com.example.foodordering.util

object FoodHelper {

    private const val BASE: Long = 311;
    private const val MOD: Long = (1e9 + 7).toLong();

    fun getIdFromFoodName(foodName: String): String {
        var id: Long = 0
        foodName.forEach { c ->
            id = (id * BASE + c.code) % MOD
        }
        return id.toString()
    }

}