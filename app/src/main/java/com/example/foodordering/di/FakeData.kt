package com.example.foodordering.di

import com.example.foodordering.domain.model.Food
import com.example.foodordering.domain.model.Bill
import com.example.foodordering.domain.model.Cart
import java.util.UUID

object FakeData {

    fun provideFood(): Food = Food(UUID.randomUUID().toString(), "Pizza", 10, emptyList(), 5)

    fun provideBills(): List<Bill> = List(20) { Bill() }

    fun provideFoods(): List<Food> = List(20) { provideFood() }
    fun provideCart(): Cart {
        val foods = provideFoods()
        return Cart(
            UUID.randomUUID().toString(),
            foods,
            foods.sumOf { it.price * it.quantity }
        )
    }
}