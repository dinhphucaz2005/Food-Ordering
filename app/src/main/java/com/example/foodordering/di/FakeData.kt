package com.example.foodordering.di

import com.example.foodordering.domain.model.Food

object FakeData {

    fun provideListFood() = List(10) { Food() }

}