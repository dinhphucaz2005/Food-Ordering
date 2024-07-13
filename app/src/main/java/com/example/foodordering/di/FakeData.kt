package com.example.foodordering.di

import com.example.foodordering.domain.model.Food
import com.example.foodordering.domain.model.Invoice

object FakeData {

    fun provideListFood() = List(10) { Food() }

    fun provideInvoices(): List<Invoice> {
        return listOf(
            Invoice(),
            Invoice(),
            Invoice(),
            Invoice(),
            Invoice(),
            Invoice(),
            Invoice(),
            Invoice(),
            Invoice(),
            Invoice(),
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