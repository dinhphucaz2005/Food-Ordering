package com.example.foodordering.domain.model

import com.example.foodordering.di.FakeData


data class CartItem(
    var food: Food = FakeData.provideFood(),
    var quantity: Int = 0,
)