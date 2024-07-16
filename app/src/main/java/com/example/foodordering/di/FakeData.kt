package com.example.foodordering.di

import com.example.foodordering.domain.model.Food
import com.example.foodordering.domain.model.Bill

object FakeData {

    fun provideListFood() = List(10) { Food() }

    fun provideBills(): List<Bill> {
        return listOf(
            Bill(),
            Bill(),
            Bill(),
            Bill(),
            Bill(),
            Bill(),
            Bill(),
            Bill(),
            Bill(),
            Bill(),
        )
    }

    fun provideFoods(): List<Food> {
        return listOf(
            Food(),
            Food(),
            Food(),
            Food(),
            Food(),
            Food(),
            Food(),
            Food(),
            Food(),
            Food(),
        )
    }
}